package com.excilys.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	private String exceptionMessage;

	public DAOException(String message) {
		this.exceptionMessage = message;
	}

	public String getMessage() {
		return exceptionMessage;
	}
}
