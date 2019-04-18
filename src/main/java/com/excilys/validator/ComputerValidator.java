package com.excilys.validator;

import java.sql.Timestamp;

import com.excilys.exception.ValidatorException;
import com.excilys.model.Computer;

public class ComputerValidator {
	
	/**
	 * Vérifie si un ordinateur est correct
	 * @param computer l'ordinateur
	 * @throws ValidatorException si l'ordinateur est incorrect
	 */
	public static void verify(Computer computer) throws ValidatorException {
		
		if (computer.getName() == null) {
			throw new ValidatorException("name");
		}
		
		Timestamp introduced = computer.getIntroduced();
		Timestamp discontinued = computer.getDiscontinued();

		if (introduced == null && discontinued != null) {
			throw new ValidatorException("discontinued");
		}

		if (introduced != null && discontinued != null) {
			if (introduced.after(discontinued)) {
				throw new ValidatorException("introduced");
			}
		}
	}
	
	// Computer name can't be null
	// Discontinued date must be null while introduced date is null
	// Introduced date is after discontinued date

}
