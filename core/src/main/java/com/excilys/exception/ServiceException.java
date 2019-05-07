package com.excilys.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public ServiceException(String message) {
		this.exceptionMessage = message;
	}

	public String getMessage() {
		return exceptionMessage;
	}

}
