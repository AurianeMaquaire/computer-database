package com.excilys.exception;

public class ModelException extends Exception {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public ModelException(String message) {
		this.exceptionMessage = message;
	}

	public String getMessage() {
		return exceptionMessage;
	}
	
}
