package com.briup.smartlabs.common.syslog.interceptor;

import com.briup.smartlabs.bean.ex.SysLog;
import com.briup.smartlabs.mapper.ex.SysLogMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

//@Component
public class LoggingInterceptor implements HandlerInterceptor{
	@Autowired
	private SysLogMapper logMapper;

	public LoggingInterceptor() {
		System.out.println("LoggingInterceptor...........");
	}

	//日志基本信息准备，借助request对象传递
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//生成日志对象。填充id/username/createDate/method/operation/params/ip
		if(handler instanceof HandlerMethod) {
			SysLog sysLog = new SysLog(); //和数据库表对应的java对象。信息载体。域对象
			sysLog.setId(UUID.randomUUID().toString());
			sysLog.setUsername("xxx");
			sysLog.setCreateDate(new Date());
			sysLog.setIp(request.getRemoteAddr());
			sysLog.setUri(request.getRequestURI());
			HandlerMethod hm = (HandlerMethod)handler;
			sysLog.setClassName(hm.getBeanType().getName());
			sysLog.setRequestMethod(request.getMethod());
			sysLog.setMethod(hm.getBeanType().getName()+"#"
							+hm.getMethod().getName());
			/*因为我们Controller方法都基本会加上@ApiOperation注解，而这个注解就是用来指定接口的操作的。
			所以可以获取注解，取到注解的value值进行操作。
			也或者我们可以自定义注解，来指定日志记录的信息(推荐！请看文档中扩展部分内容)。
			*/
			ApiOperation ao = hm.getMethodAnnotation(ApiOperation.class);
			if(ao!=null) {
				sysLog.setOperation(ao.value());
			}
			//获取所有请求参数
			Map<String,String[]> paramMap = request.getParameterMap();
			ObjectMapper om = new ObjectMapper();
			//将请求参数转换成json字符串
			String paramJson = om.writeValueAsString(paramMap);
			sysLog.setParams(paramJson);
			//每有一个新的请求，就记录一个新的日志，因此将日志信息和request绑定在一起
			request.setAttribute("sysLog", sysLog);
		}
		//System.out.println("pre request:"+request);
		return true;
	}

	//日志信息写入数据库
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//从request中，将请求进来时创建的日志对象，获取出来。
		Object obj = request.getAttribute("sysLog");
		//判断如果取出来的对象不为空，并且类型是SysLog类型。
		if((obj != null) && (obj instanceof SysLog)) {
			SysLog log = (SysLog)obj; //强制类型转换为SysLog类型
			//计算本次操作使用的时长
			long end = System.currentTimeMillis();
			long begin = log.getCreateDate().getTime();
			log.setTime(end-begin);

			//日志记录写入数据库
			logMapper.insert(log);
		}
	}
}
