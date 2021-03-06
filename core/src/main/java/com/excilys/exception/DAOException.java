package com.excilys.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String exceptionMessage;

	public DAOException(String message) {
		this.exceptionMessage = message;
	}

	@Override
	public String getMessage() {
		return exceptionMessage;
	}
	
}
