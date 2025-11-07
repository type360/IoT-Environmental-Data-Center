package com.briup.smartlabs.gateway.config;

import com.briup.smartlabs.gateway.utils.MessageConvertUtils;
import gnu.io.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//表明该类是spring的一个配置类，串口相关配置
@Configuration
public class CommPortConfig {
	
	@Value("${smartlabs.serialport.name}")
	private String serialPortName;
	
	@Value("${smartlabs.serialport.baudrate}")
	private int baudrate;

	//中转的 协调器，应该是板子上的Zigbee模块
	@Value("${smartlabs.gateway.address}")
	private String gatewayAdres;

	//参考Zigbee协议
	@Value("${smartlabs.gateway.protocol.prefix}")	//前导符0xef 0xef + 帧长度0x00
	private String prefix;
	
	@Value("${smartlabs.gateway.protocol.suffix}")	//帧尾
	private String suffix;
	
	@Value("${smartlabs.gateway.protocol.adress}")	//测试用短地址 ???
	private String protocolAdres;
	
	//实例化串口对象 并写入spring容器中
	@Bean("spOutput")
	public OutputStream getSerialPortOutputStream() throws IOException {
		OutputStream os =  getCommPort().getOutputStream();
		// 为什么？ Zigbee组网固定要求 先发送 9130346C
		System.out.println("注册网关"+(prefix+protocolAdres+gatewayAdres+suffix));
		//注册网关
		os.write(MessageConvertUtils.toByteArray(
				prefix+protocolAdres+gatewayAdres+suffix));
		os.flush();
		return os;
	}
	
	//获取串口输入流，用来读取串口返回的数据
	@Bean("spInput")
	public InputStream getSerialPortInputStream() throws IOException {
		return getCommPort().getInputStream();
	}
	
	//打开串口，获取串口示例对象
	@Bean("commPort") //自定义beanName，默认名字为方法名。
	public CommPort getCommPort() {
		CommPort cp = null;
		try {
			CommPortIdentifier cpi =  //通过端口标识符获取
					CommPortIdentifier.getPortIdentifier(serialPortName);
			//				name有什么用处？			延迟时间2s
			cp = cpi.open("smartlabs_gateway", 2000); //打开串口
			if(cp instanceof SerialPort) {
				//串口最基本参数设置
				SerialPort sp = (SerialPort)cp;
				sp.setSerialPortParams(baudrate, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
				
			}
		} catch (NoSuchPortException e) {
			throw new RuntimeException(
					"获取CommPortIdentifier失败！该端口不存在！",e);
		} catch (PortInUseException e) {
			throw new RuntimeException("串口被占用！",e);
		} catch (UnsupportedCommOperationException e) {
			throw new RuntimeException("串口参数不支持！",e);
		}
		//返回 串口对象
		return cp;
	}
}
