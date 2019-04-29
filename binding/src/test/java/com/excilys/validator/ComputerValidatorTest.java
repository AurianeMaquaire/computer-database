package com.excilys.validator;

import static org.junit.Assert.assertEquals;

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
	
	@Test
	public void testException1() {
		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
		computer = new Computer(0L, null, introduced, discontinued, null);
		try {
			ComputerValidator.verify(computer);
		} catch (ValidatorException e) {
			assertEquals(e.getMessage(), "exceptionName");
		}
	}
	
	@Test
	public void testException2() {
		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
		computer = new Computer(0L, "name", null, discontinued, null);
		try {
			ComputerValidator.verify(computer);
		} catch (ValidatorException e) {
			assertEquals(e.getMessage(), "exceptionDiscontinued");
		}
	}
	
	@Test
	public void testException3() {
		Timestamp introduced = Timestamp.valueOf("2010-01-01 00:00:00");
		Timestamp discontinued = Timestamp.valueOf("2000-01-01 00:00:00");
		computer = new Computer(0L, "name", introduced, discontinued, null);
		try {
			ComputerValidator.verify(computer);
		} catch (ValidatorException e) {
			assertEquals(e.getMessage(), "exceptionIntroduced");
		}
	}
	
}
