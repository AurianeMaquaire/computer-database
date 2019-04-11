package com.excilys.exception;

public class ServletException extends Exception {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public ServletException(String message) {
		this.exceptionMessage = message;
	}

	public String getMessage() {
		return exceptionMessage;
	}
	
}
