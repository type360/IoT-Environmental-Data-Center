package com.briup.smartlabs.web.interceptor;

import com.briup.smartlabs.bean.SysUser;
import com.briup.smartlabs.common.exceptions.NoAuthorizationException;
import com.briup.smartlabs.common.utils.AuthenticationHolder;
import com.briup.smartlabs.common.utils.JwtUtils;
import com.briup.smartlabs.mapper.ex.SysUserExMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	private SysUserExMapper userExMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getMethod().equals(
				RequestMethod.OPTIONS.name())) {
			return true;
		}
		String token = request.getHeader("Authorization");
		System.out.println("token .... "+token);
		if(StringUtils.isEmpty(token)) {
			throw new NoAuthorizationException("请先登录！");
		}
		try {
			String username = JwtUtils.getUsername(token);
			SysUser user = userExMapper.loadByName(username);
			AuthenticationHolder.setUsers(user);
			return true;
		}catch (ExpiredJwtException e) {
			throw new NoAuthorizationException("令牌过期，请重新登录！",e);
		}catch (Exception e) {
			throw new NoAuthorizationException("无效令牌，请登录！",e);
		}
	}
}



