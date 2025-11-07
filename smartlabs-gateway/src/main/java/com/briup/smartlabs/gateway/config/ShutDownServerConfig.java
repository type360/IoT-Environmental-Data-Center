package com.briup.smartlabs.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

//配置网关关闭服务 监听程序
@Configuration
public class ShutDownServerConfig {
	@Value("${smartlabs.server.shutdown}")
	private int shutdownPort;
	
	@Autowired
	private AsyncTaskConfig task;
	
	@Bean("shutdownServer")
	public ServerSocket getShutdownServer() throws IOException {
		ServerSocket shutdown = new ServerSocket(shutdownPort);
		//启动一个异步调用，监听客户端是否有请求关闭网关程序。
		task.closeListener(shutdown);
		return shutdown;
	}
	
}
