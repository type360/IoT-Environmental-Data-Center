package com.briup.smartlabs.common.exceptions;
//拒绝访问
public class DenyAccessException extends RuntimeException{
	private static final long serialVersionUID = -8982134414240140918L;

	public DenyAccessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DenyAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DenyAccessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DenyAccessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DenyAccessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
