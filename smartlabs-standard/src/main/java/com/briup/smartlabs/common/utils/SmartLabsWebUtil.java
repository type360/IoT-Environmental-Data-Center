package com.briup.smartlabs.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SmartLabsWebUtil {

	//获取请求对象
	public static HttpServletRequest getRequest() {
		RequestAttributes ra = 
				RequestContextHolder.getRequestAttributes();
		if(ra instanceof ServletRequestAttributes) {
			ServletRequestAttributes sra = 
					(ServletRequestAttributes)ra;
			return sra.getRequest();
		}
		return null;
	}
}
