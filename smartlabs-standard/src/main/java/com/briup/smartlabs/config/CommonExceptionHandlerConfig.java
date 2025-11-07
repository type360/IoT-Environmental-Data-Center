package com.briup.smartlabs.config;

import com.briup.smartlabs.common.exceptions.DataValidateException;
import com.briup.smartlabs.common.exceptions.DenyAccessException;
import com.briup.smartlabs.common.exceptions.NoAuthorizationException;
import com.briup.smartlabs.common.exceptions.UsernameAndPasswordException;
import com.briup.smartlabs.common.utils.Response;
import com.briup.smartlabs.common.utils.ResponseCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理
@RestControllerAdvice
public class CommonExceptionHandlerConfig {

	@ExceptionHandler(DataValidateException.class)
	public Response<String> handlerDataValidateException(
			DataValidateException ex){
		ex.printStackTrace();
		return Response.error(ResponseCodeEnum.DATA_WRONG,ex.getMessage());
	}
	
	@ExceptionHandler(NoAuthorizationException.class)
	public Response<String> handlerNoAuthException(NoAuthorizationException ex){
		ex.printStackTrace();
		return Response.error(ResponseCodeEnum.USER_NOT_LOGIN, ex.getMessage());
	}
	
	@ExceptionHandler(UsernameAndPasswordException.class)
	public Response<String> handlerLoginException(UsernameAndPasswordException ex){
		ex.printStackTrace();
		return Response.error(ResponseCodeEnum.USER_LOGIN_ERROR, ex.getMessage());
	}
	
	@ExceptionHandler(DenyAccessException.class)
	public Response<String> handlerDenyAccessException(DenyAccessException ex){
		ex.printStackTrace();
		return Response.error(ResponseCodeEnum.USER_ACCESS_DENY, ex.getMessage());
	}
	
	
	@ExceptionHandler(Exception.class)
	public Response<String> handlerException(Exception ex){
		ex.printStackTrace();
		return Response.error(ResponseCodeEnum.ERROR,ex.getMessage());
	}
}



