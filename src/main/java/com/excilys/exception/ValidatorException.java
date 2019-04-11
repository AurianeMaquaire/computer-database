package com.excilys.exception;

public class ValidatorException extends Exception {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public ValidatorException(String message) {
		this.exceptionMessage = message;
	}

	public String getMessage() {
		return exceptionMessage;
	}
	
}
