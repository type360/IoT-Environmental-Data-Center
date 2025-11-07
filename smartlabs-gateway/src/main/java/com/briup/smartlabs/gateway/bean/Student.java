package com.briup.smartlabs.gateway.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity		//表明是一个受JPA管理的可持久化的实体对象
@Table(name = "s_stu")	//表明当前实体对象和哪个数据库表映射。
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sno;
	@Column(name = "name",unique = true,nullable = false)
	private String name;
	@Column
	private int age;
	@Column
	private String gender;
}





