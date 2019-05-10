package com.excilys.exception;

public class ValidatorException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String exceptionMessage;

	public ValidatorException(String message) {
		this.exceptionMessage = message;
	}
	
	@Override
	public String getMessage() {
		return exceptionMessage;
	}
	
}
