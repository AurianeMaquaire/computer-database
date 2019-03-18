package com.excilys.model;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;


public class CompanyTest {
	
	public CompanyTest() {
		super();
	}
	
	Company company;
	
	@Before
    public void setUp() {
		company = new CompanyBuilder().withId(null)
									  .withName(null)
									  .build();
    }
	
	@Test
    public void testNull() {
		assertNull(company.getId());
		assertNull(company.getName());
	}
	
	
}
