package com.briup.smartlabs.gateway.config;

import com.briup.smartlabs.gateway.utils.DeviceAdressConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.InputStream;
import java.util.Date;

//定时任务功能实现类
@Configuration
public class ScheduleTaskConfig {
	
	@Autowired
	private InputStream is; //注册串口输入流，用来读取串口返回的数据。
	
	@Autowired 
	ServerConfig serverConfig;
	
	@Autowired
	private AsyncTaskConfig asyncTask;

	@Scheduled(cron = "0 0/1 * * * *") //每一分钟触发一次
	public void gather() throws Exception{
		System.out.println("定时传感设备数据采集，触发时间："+new Date());

		// 温度  湿度   CO2  光照   紫外线	PM2.5
		for(String device : DeviceAdressConstants.devices) {
			// 通过串口发送数据(先转换为完整)
			serverConfig.sendMessage(device);
			int data = -1;
			String dataStr = "";
			Thread.sleep(500);
			// 通过串口，逐个字节读取响应数据
			while((data = is.read())!=-1) {
				String s = Integer.toHexString(data);
				// 拼接16进制数据: 每个字节为 02 或 2F
				dataStr += s.length()==1 ? 0+s : s;
			}
			System.out.println("读取到的数据返回为: "+dataStr);

			//调用异步处理程序，解析环境数据
			asyncTask.parseEnviroment(dataStr);
		}
	}
	

	// 定时任务测试
	//@Scheduled(fixedDelay = 1000)
	//@Scheduled(fixedRate = 1000)
	@Scheduled(cron = "0 0/1 * * * *")
	public void test() {
		System.out.println("我是定时任务，触发时间："+new Date());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("定时任务结束时间："+new Date());
	}
}
