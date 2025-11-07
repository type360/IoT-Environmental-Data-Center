package com.briup.smartlabs.gateway.config;

import com.briup.smartlabs.gateway.utils.CrcModbusCheckUtils;
import com.briup.smartlabs.gateway.utils.MessageConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//配置类，配置基于socket请求的服务。
@Configuration
public class ServerConfig {
	@Value("${smartlabs.server.startup}")
	private int startup;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Value("${smartlabs.gateway.protocol.adress}")
	private String protocolAdres;
	
	@Value("${smartlabs.gateway.protocol.prefix}")
	private String prefix;
	
	@Value("${smartlabs.gateway.protocol.suffix}")
	private String suffix;
	
	@Autowired
	@Qualifier("spOutput")
	private OutputStream spOut;

	// 借助串口发送数据
	// 					   温度："2430A41A"
	public void sendMessage(String msg) {
		msg = getFullProtocol(msg);
		try {
			synchronized (spOut) {
				System.out.println("即将写出的通信数据："+msg);
				spOut.write(MessageConvertUtils.toByteArray(msg));
				spOut.flush();
			}
		}catch (IOException e) {
			System.out.println("发送协议:"+msg+"出错！");
			e.printStackTrace();
		}
	}
	
	//拼接完整的硬件协议		"2430A41A"
	public String getFullProtocol(String msg) {
		char type = msg.charAt(2); //第三个字符位置如果是2，代表控制指令，应该加CRC校验
		// 控制指令，应该加CRC校验（数据 = 数据 + 校验）
		if(type == '2') {
			String crc = CrcModbusCheckUtils.getCRC3(
					MessageConvertUtils.toByteArray(msg));
			msg = msg+crc;
		}
		
		return prefix+protocolAdres+msg+suffix;
	}

	//启动9999服务器，等待客户端的连接，收到客户端发送过来的数据 转发到串口
	@Bean
	public ServerSocket getServerSocket() {
		try {
			ServerSocket server = new ServerSocket(startup);
			//System.out.println("taskExecutor......"+taskExecutor);
			//启动一个线程，开启监听。
			taskExecutor.execute(() -> {//lambda表达式，函数式编程。
				System.out.println(Thread.currentThread().getName()+"...启动服务监听！");
				while(true) {
					try {
						Socket socket = server.accept();
						//分配一个线程，处理客户端发送的socket请求。
						taskExecutor.execute(()->{
							System.out.println(Thread.currentThread().getName()+" 处理本次"+socket+"请求。。");
							//1. 读取客户端发送过来的请求协议（获取socket的输入流）
							try(Scanner sc = new Scanner(socket.getInputStream())){
								String msg = null;
								while(sc.hasNextLine()) {
									msg = sc.nextLine();
									System.out.println("读取到的msg信息："+msg);
									//2. 判断该协议请求是否需要CRC校验。
									//3. 拼接成一个合法的硬件通信协议(prefix+adres+msg[+CRC]+suffix)
									msg = getFullProtocol(msg);
									//4. 通过串口，写入硬件通信协议(使用串口输出流)。进行通信。
									synchronized (spOut) {
										System.out.println("即将写出的通信数据："+msg);
										//将16进制的字符串，转换成byte[]，然后发送给串口
										spOut.write(MessageConvertUtils.toByteArray(msg));
										spOut.flush();
									}
								}
							}catch (IOException e) {
								e.printStackTrace();
							}finally {
								try {
									socket.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
					} catch (IOException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					
				}
			});
			return server;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("服务创建失败！",e);
		} 
	}
}
