package com.briup.smartlabs.bean.ex;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class SysLog {
	private String id;
	private String username;
	private String operation;
	private String className; //访问的是那个类
	private String method;	//
	private String params;
	private long time;
	private String ip;
	private Date createDate;
	private String uri;
	private String requestMethod;
	
}
