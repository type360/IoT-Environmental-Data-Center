package com.briup.smartlabs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SmartEnvDetail {
	private String id;
	private String deviceAdres;
	private int  type; //当前数据的类型(0-温度，1-湿度，。。。)
	private double value; //环境数据值
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gatherDate;
}