package com.briup.smartlabs.common.utils;

import com.briup.smartlabs.bean.SysUser;

//该对象用来存放当前认证通过的用户信息
public class AuthenticationHolder {
	public static final ThreadLocal<SysUser> userContainer = 
			new ThreadLocal<>();
	
	public static void setUsers(SysUser user) {
		userContainer.set(user);
	}
	
	public static SysUser getCurrentUser() {
		return userContainer.get();
	}

	//返回当前用户名
	public static String getCurrentUserName() {
		SysUser user = userContainer.get();
		if(user!=null) {
			return user.getUsername();
		}
		return null;
	}

	//放回当前用户id
	public static Long getCurrentUserId() {
		SysUser user = userContainer.get();
		if(user!=null) {
			return user.getUserId();
		}
		return null;
	}
}
