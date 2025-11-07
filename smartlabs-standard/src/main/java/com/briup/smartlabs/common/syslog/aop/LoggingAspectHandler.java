package com.briup.smartlabs.common.syslog.aop;

import com.briup.smartlabs.bean.ex.SysLog;
import com.briup.smartlabs.common.syslog.util.LogHolder;
import com.briup.smartlabs.common.utils.AuthenticationHolder;
import com.briup.smartlabs.common.utils.SmartLabsWebUtil;
import com.briup.smartlabs.mapper.ex.SysLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

//日志模块功能
@Component
@Aspect
public class LoggingAspectHandler {
	@Autowired
	private SysLogMapper logMapper;

	@Pointcut("execution(* com.briup.smartlabs.web.controller.*.*(..))")
	public void logPointcut() {}
	
	@Before("logPointcut()")
	public void initLog(JoinPoint jp) {
		System.out.println("拦截方法名......"+jp.getSignature().getName());
		SysLog log = new SysLog();
		log.setId(UUID.randomUUID().toString());
		log.setCreateDate(new Date());
		Signature sign = jp.getSignature();
		//连接点方法所在类的名字
		log.setClassName(sign.getDeclaringTypeName());
		//log的方法=连接点方法类名:方法名
		log.setMethod(
			sign.getDeclaringTypeName()+":"+sign.getName());
		log.setUsername(AuthenticationHolder.getCurrentUserName());
		log.setOperation(sign.getName());
		HttpServletRequest req = SmartLabsWebUtil.getRequest();
		if(req!=null) {
			log.setIp(req.getRemoteAddr()); //request
			log.setParams("");  //request param
			log.setRequestMethod(req.getMethod()); // request
			log.setUri(req.getRequestURI());	//request
			LogHolder.setLog(log);
		}
	}
	
	@After("logPointcut()")
	public void insertLog() {
		SysLog log = LogHolder.getLog();
		if(log!=null) {
			log.setTime(System.currentTimeMillis()-log.getCreateDate().getTime());
			//将日志信息写入数据库
			logMapper.insert(log);
		}
	}
}
