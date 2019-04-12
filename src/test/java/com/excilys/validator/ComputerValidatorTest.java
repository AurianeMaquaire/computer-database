package com.excilys.validator;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ValidatorException;
import com.excilys.model.Computer;

public class ComputerValidatorTest {
	
	Computer computer;
	
	public ComputerValidatorTest() {
		super();
	}
	
	@Before
	public void setUp() {
		computer = new Computer();
	}
	
	@Test
	public void testOk() throws ValidatorException {
		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
		computer = new Computer(0L, "name", introduced, discontinued, null);
		ComputerValidator.verify(computer);
	}
	
	@Test(expected = ValidatorException.class)
	public void testException1() throws ValidatorException {
		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
		computer = new Computer(0L, null, introduced, discontinued, null);
		ComputerValidator.verify(computer);
	}
	
	@Test(expected = ValidatorException.class)
	public void testException2() throws ValidatorException {
		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
		computer = new Computer(0L, "name", null, discontinued, null);
		ComputerValidator.verify(computer);
	}
	
	@Test(expected = ValidatorException.class)
	public void testException3() throws ValidatorException {
		Timestamp introduced = Timestamp.valueOf("2010-01-01 00:00:00");
		Timestamp discontinued = Timestamp.valueOf("2000-01-01 00:00:00");
		computer = new Computer(0L, "name", introduced, discontinued, null);
		ComputerValidator.verify(computer);
	}
	
}
