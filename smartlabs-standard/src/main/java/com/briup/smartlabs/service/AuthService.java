package com.briup.smartlabs.service;

import com.briup.smartlabs.web.vm.UserToken;

public interface AuthService {

	UserToken login(String username, String password);
	//生成的验证码，保存到数据库。
	void saveOrUpdateKaptcha(String formId,String code);
	
	//根据用户输入的表单id和验证码进行，校验
	boolean verfiyKaptcha(String formId,String code);
	
	String getPermissions(); //用来获取当前登录用户的权限
}
