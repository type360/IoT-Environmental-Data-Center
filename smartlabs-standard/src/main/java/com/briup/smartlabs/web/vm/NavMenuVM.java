package com.briup.smartlabs.web.vm;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@ApiModel("导航菜单模型数据")
@Getter
@Setter
public class NavMenuVM {
	
	private Long id;		//菜单唯一标识符
	private String icon;   //菜单图标
	private String index; //路由的路径，如果是父目录为null
	private String title; //菜单名儿。
	private Set<NavMenuVM> subs;  //子菜单
}
