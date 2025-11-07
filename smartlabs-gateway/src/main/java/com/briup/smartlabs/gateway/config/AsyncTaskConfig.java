package com.briup.smartlabs.gateway.config;

import com.briup.smartlabs.gateway.bean.Environment;
import com.briup.smartlabs.gateway.dao.EnvDao;
import com.briup.smartlabs.gateway.utils.DeviceAdressConstants.DeviceEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gnu.io.CommPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

//异步任务
@Component
public class AsyncTaskConfig {
	
	@Autowired
	@Qualifier("spInput")  //按照名字
	private InputStream is;
	
	@Autowired
	private OutputStream os;
	
	@Autowired
	private CommPort cp;
	
	@Autowired
	@Qualifier("getServerSocket")
	private ServerSocket server;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	//【解析从串口输入流中获取的msg】 异步调用程序
	// 解析出有用的环境数据写入数据库。redis
	@Async
	public void parseEnviroment(String msg) {
		// 1.解析上传的数据内容，以PM2.5为例
		//    12字节               00 26: 具体数据值   51 54: 校验值
		//efef 0d 1234 | 43 43 02 00 26 51 54 | ff
		if(msg.length()<20) return; //如果读取到的环境数据长度低于20,那么代表数据为非法数据
		// 截器 有效数据+帧尾【去除前缀、帧长度、短地址】
		msg = msg.substring(10, msg.length()); //43430200265154ff
		String deviceAdres = msg.substring(0, 2); //43
		if(msg.charAt(2) == '4') {	//第三位：功能码 4 代表 响应指令
			/* 功能码低4位
				'0'->无数据部分
				'1'->数据部分为定长整型数，
				'2'->数据部分为[sg]15.16定点数
				'3'->不定长整型数
				'4'->不定长浮点数，'5'->ASCII标准字符串数据
			*/
			char dataType = msg.charAt(3);
			if(dataType=='0') return ; //0代表没有数据位。
			// 获取数据长度 02
			int length = Integer.parseInt(msg.substring(4, 6), 16);
			// 截取有效数据 0026
			String dataStr = msg.substring(6, 6+2*length);
			System.out.println("dataStr...."+dataStr);

			double data = 0.0d;
			if(dataType=='1'||dataType=='3') {
				//数据部分为整型数(定长或不定长)，将有效数据转换成 double类型
				data = Integer.parseInt(dataStr, 16);
			}else if(dataType=='2') { //高15位为整数位，低16位为小数部分
				//数据部分为 浮点数  ????为什么小数 / 100000.d
				if(length!=4) return ; //如果数据位长度不为4字节，就代表不是合法的15.16数据。
				data = Integer.parseInt(dataStr.substring(0, 4),16) //整数部分
				     + Integer.parseInt(dataStr.substring(4, 8),16)/100000.d; //小数部分
			}else {
				return; //其他格式不处理。
			}

			//2.将上述解析得到的数据，封装成Environment对象
			Environment env = new Environment();
			env.setId(UUID.randomUUID().toString());
			env.setDeviceAdres(deviceAdres);
			env.setGatherDate(new Date());
			env.setValue(data);
			env.setType(DeviceEnum.getByAdres(deviceAdres).getType());
			System.out.println("env:"+env);

			//3.写入数据库中
			insertToDB(env);

			//4.推送到redis中
			publishToRedis(env);
		}
	}
	
	@Autowired
	StringRedisTemplate strRedisTemp;

	//推送到redis中
	@Async
	public void publishToRedis(Environment env) {
		ObjectMapper om = new ObjectMapper();
		try {
			// 数据序列化为json格式
			String message = om.writeValueAsString(env);
			// 写入到redis中
			//  	void convertAndSend(String channel, Object message)
			strRedisTemp.convertAndSend(env.getDeviceAdres(), message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private EnvDao envDao;
	
	@Async
	public void insertToDB(Environment env) {
		//插入数据库
		envDao.save(env);
	}

	@Async
	public void closeListener(ServerSocket shutdownServer) {
		// 串口输入流，串口输出流，串口对象，网关服务监听，线程池
		System.out.println(Thread.currentThread().getName()+" 启动关闭服务监听！");
		// 等待客户端连接，一旦连接成功，读取客户端发送的数据，如果是bye，关闭相关资源
		try(Socket socket = shutdownServer.accept();
			Scanner sc = new Scanner(socket.getInputStream())){
			String msg = sc.nextLine();
			if("bye".equals(msg)) {
				//线程池
				taskExecutor.shutdown();
				//9999服务器
				server.close();
				//串口输入
				is.close();
				//串口输出
				os.close();
				//串口对象
				cp.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				shutdownServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}





