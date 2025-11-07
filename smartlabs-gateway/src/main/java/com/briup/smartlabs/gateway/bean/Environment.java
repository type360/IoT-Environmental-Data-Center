package com.briup.smartlabs.gateway.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
//环境数据对象
@Entity
@Table(name = "smart_env")
public class Environment implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name = "device_adres")
	private String deviceAdres;
	@Column
	private int  type; //当前数据的类型(0-温度，1-湿度，。。。)
	@Column
	private double value; //环境数据值
	@Column(name = "gather_date")
	private Date gatherDate;
}
