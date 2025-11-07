package com.briup.smartlabs;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.briup.smartlabs.mapper"})
@EnableSwagger2Doc //启动swagger
public class SmartlabsBaoshiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartlabsBaoshiApplication.class, args);
	}

}
