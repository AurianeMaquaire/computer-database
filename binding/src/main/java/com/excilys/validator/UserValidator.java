package com.excilys.validator;

import com.excilys.exception.ValidatorException;
import com.excilys.model.User;

public class UserValidator {

	/**
	 * Vérifie si un utilisateur est correct
	 * @param user l'utilisateur
	 * @throws ValidatorException si l'utilisateur est incorrect
	 */
	public static void verify(User user) throws ValidatorException {
		if ("".equals(user.getUsername())) {
			throw new ValidatorException("exceptionEmptyUsername");
		}
	}
	
	
}
