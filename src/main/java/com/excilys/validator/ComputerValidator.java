package com.excilys.validator;


import java.sql.Timestamp;

import com.excilys.exception.ValidatorException;
import com.excilys.model.Computer;

public class ComputerValidator {

	public static void verify(Computer computer) throws ValidatorException {

		Timestamp introduced = computer.getIntroduced();
		Timestamp discontinued = computer.getDiscontinued();

		if (introduced == null && discontinued != null) {
			throw new ValidatorException("Discontinued date can't be null while introduced date is null");
		}

		if (introduced != null && discontinued != null) {
			if (introduced.after(discontinued)) {
				throw new ValidatorException("Introduced date is after discontinued date");
			}
		}
	}

}
