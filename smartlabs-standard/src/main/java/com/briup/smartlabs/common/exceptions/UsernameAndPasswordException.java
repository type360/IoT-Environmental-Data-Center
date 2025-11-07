package com.briup.smartlabs.common.exceptions;

public class UsernameAndPasswordException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UsernameAndPasswordException() {
		super();
	}

	public UsernameAndPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameAndPasswordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameAndPasswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameAndPasswordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
