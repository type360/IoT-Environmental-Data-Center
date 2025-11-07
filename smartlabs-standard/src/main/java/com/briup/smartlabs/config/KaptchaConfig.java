package com.briup.smartlabs.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

//验证码配置类
@Configuration //该注解代表本类是一个Spring的配置类。
public class KaptchaConfig {
	
	@Bean //会将方法的返回值，放入Spring的ioc容器中。
	public Producer createProducer() {
		DefaultKaptcha producer = new DefaultKaptcha();
		//验证码属性准备
		Properties properties = new Properties();
		properties.setProperty("kaptcha.textproducer.char.string", 
								"0123456789abcdefghijklmnopqrstuvwxyz");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.image.width", "150");
		properties.setProperty("kaptcha.image.height", "50");
		Config config = new Config(properties);
		//验证码属性配置
		producer.setConfig(config);
		return producer;
	}
}





