package com.excilys.model;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ComputerTest {
	
	public ComputerTest() {
		super();
	}
	
	Computer computer;
	
	@Before
    public void setUp() {
		computer = new ComputerBuilder().withId(null)
										.withName(null)
										.withIntroduced(null)
										.withDiscontinued(null)
										.withCompany(null)
										.build();
    }
	
	@Test
    public void testNull() {
		assertNull(computer.getId());
		assertNull(computer.getName());
		assertNull(computer.getIntroduced());
		assertNull(computer.getDiscontinued());
		assertNull(computer.getCompany());
	}

}
