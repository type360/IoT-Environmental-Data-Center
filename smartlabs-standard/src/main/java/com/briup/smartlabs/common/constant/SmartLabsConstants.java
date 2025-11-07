package com.briup.smartlabs.common.constant;

//存放当前系统常量
public class SmartLabsConstants {
	public static final String SUPER_ADMIN = "admin";
	public static final Long SUPER_ADMIN_ID = 1L;
	
	public enum MenuType {
		DIR(0,"目录项"),
		MENU(1,"菜单项"),
		BUTTON(2,"按钮项");

		int type;
		String name;
		private MenuType(int type,String name) {
			this.type = type;
			this.name = name;
		}
		public int getType() {
			return type;
		}
		public String getName() {
			return name;
		}
	}
}
