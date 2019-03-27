package com.excilys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class CompanyBuilderTest {
	
	Company company1;
	Company company2;
	CompanyBuilder companyBuilder1;
	
	@Before
	public void setUp() {
		company1 = new Company();
		company2 = new Company();
		companyBuilder1 = new CompanyBuilder();
	}
	
	@Test
	public void testEmpty() {
		company1 = companyBuilder1.empty().build();
		company2 = new Company(0L, null);
		assertEquals(company1, company2);
	}
	
	@Test
	public void testId() {
		company1 = companyBuilder1.withId(1L).build();
		company2 = new Company(1L, null);
		assertEquals(company1, company2);
	}
	
	@Test
	public void testName() {
		company1 = companyBuilder1.withName("company").build();
		company2 = new Company(0L, "company");
		assertEquals(company1, company2);
	}
	
	@Test
	public void testIdAndName() {
		company1 = companyBuilder1.withId(1L).withName("company").build();
		company2 = new Company(1L, "company");
		assertEquals(company1, company2);
		
		company1 = companyBuilder1.withIdAndName(2L, "Company").build();
		assertNotEquals(company1, company2);
	}
	
}
