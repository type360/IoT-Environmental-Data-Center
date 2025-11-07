package com.briup.smartlabs.service.impl;

import com.briup.smartlabs.bean.SysKaptcha;
import com.briup.smartlabs.bean.SysMenu;
import com.briup.smartlabs.bean.SysUser;
import com.briup.smartlabs.common.constant.SmartLabsConstants;
import com.briup.smartlabs.common.exceptions.DataValidateException;
import com.briup.smartlabs.common.exceptions.UsernameAndPasswordException;
import com.briup.smartlabs.common.utils.AuthenticationHolder;
import com.briup.smartlabs.common.utils.JwtUtils;
import com.briup.smartlabs.mapper.SysKaptchaMapper;
import com.briup.smartlabs.mapper.SysMenuMapper;
import com.briup.smartlabs.mapper.ex.SysMenuExMapper;
import com.briup.smartlabs.mapper.ex.SysUserExMapper;
import com.briup.smartlabs.service.AuthService;
import com.briup.smartlabs.web.vm.UserToken;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	SysUserExMapper userExMapper;
	
	@Autowired
	SysKaptchaMapper kaptchaMapper;
	
	@Autowired
	SysMenuExMapper menuExMapper;
	
	@Autowired
	SysMenuMapper menuMapper;
	
	//登录认证
	@Override
	public UserToken login(String username, String password) {
		SysUser user = userExMapper.loadByName(username);
		if(user==null) {
			throw new UsernameAndPasswordException("用户名或密码错误！");
		}
		if(!StringUtils.equals(password, user.getPassword())) {
			throw new UsernameAndPasswordException("用户名或密码错误！");
		}
		//根据user对象生成token对象
		String token = JwtUtils.generateJwtStr(user);
		UserToken ut = new UserToken();
		ut.setUsername(user.getUsername());
		ut.setToken(token);
		return ut;
	}

	//验证码生成或更新
	@Override
	public void saveOrUpdateKaptcha(String formId, String code) {
		if(StringUtils.isBlank(formId)) {
			throw new DataValidateException("无法生成验证码，请指定表单id!");
		}
		SysKaptcha kaptcha = kaptchaMapper.selectByPrimaryKey(formId);
		if(kaptcha!=null) {
			kaptcha.setKaptcha(code);
			kaptcha.setExpire(new Date(System.currentTimeMillis()+10*60*1000));
			kaptchaMapper.updateByPrimaryKey(kaptcha);
		}else {
			kaptcha = new SysKaptcha();
			kaptcha.setFormId(formId);
			kaptcha.setKaptcha(code);
			kaptcha.setExpire(new Date(System.currentTimeMillis()+10*60*1000));
			kaptchaMapper.insert(kaptcha);
		}
	}

	//验证码 校验
	public boolean verfiyKaptcha(String formId, String code) {
		System.out.println("formId:"+formId+"...code;"+code);
		if(StringUtils.isBlank(formId)||StringUtils.isEmpty(code)) {
			throw new DataValidateException("请输入验证码相关信息！");
		}

		//数据库查询获取验证码对象
		SysKaptcha kaptcha = kaptchaMapper.selectByPrimaryKey(formId);
		if((kaptcha==null)||(!StringUtils.equals(code, kaptcha.getKaptcha()))) {
			throw new DataValidateException("验证码错误！");
		}

		//超时验证
		if(kaptcha.getExpire().before(new Date())) {
			throw new DataValidateException("验证码失效,重新生成！");
		}
		//验证通过
		return true;
	}

	@Override
	public String getPermissions() { 
		//1. 获取当前登录用户的信息
		String name = AuthenticationHolder.getCurrentUserName();
		//2. 判断当前登录用户是否是Admin（超级管理员）
		List<SysMenu> menus = null;
		if(name.equals(SmartLabsConstants.SUPER_ADMIN)) {
			//2.1 如果是，查询所有菜单权限，返回。
			menus = menuMapper.selectAll();
		}else {
			//2.1 如果不是，根据当前登录用户，查询该用户所拥有角色下的所有菜单权限。
			menus = menuExMapper.findMenuByUsername(name);
		}
		String perms = "";
		if(ObjectUtils.isNotEmpty(menus)) {
			for(SysMenu menu : menus) {
				if(StringUtils.isNotEmpty(menu.getPerms())) {
					perms += menu.getPerms()+",";
				}
			}
		}
		return perms;
	}
}




