package com.briup.smartlabs.common.exceptions;

public class NoAuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NoAuthorizationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoAuthorizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoAuthorizationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoAuthorizationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoAuthorizationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
