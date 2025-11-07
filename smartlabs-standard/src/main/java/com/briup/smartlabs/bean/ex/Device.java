package com.briup.smartlabs.bean.ex;

import lombok.Getter;
import lombok.Setter;

/*
 * 设备信息
 */
@Setter
@Getter
public class Device {
	private String id;
	private String deviceName;//设备名称
	private String deviceAddr;//设备地址
	private String deviceType; //设备类型编号
	private String labsId; //实验室id
	private String open;  //打开协议
	private String close; //关闭协议
	private int maxThreshold; //  范围控制类设备的最大区间
	private int minThreshold; //  范围控制类设备的最小区间。
	
	private String typeName; //设备类型名
	
	//private DeviceType type; //所关联的设备类型对象。
}


