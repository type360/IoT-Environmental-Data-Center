package com.briup.smartlabs.bean.ex;

import lombok.Data;

import java.util.List;

@Data
public class SysMenuEx {
	//目录， 菜单，按钮
	private Long menuId;
	private String name;
	private List<SysMenuEx> subs;
}
