package com.excilys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class CompanyTest {
	
	private static CompanyBuilder companyBuilder1 = new CompanyBuilder();
	private static CompanyBuilder companyBuilder2 = new CompanyBuilder();
	
	static Company company1;
	static Company company2;
	
	public CompanyTest() {
		super();
	}
	
	@Before
    public void setUp() {
		company1 = companyBuilder1.empty().build();
		company2 = companyBuilder2.empty().build();
    }
	
	
	@Test
	public void testCompany() {
		assertEquals(company1, company1);
		assertNotNull(company1);
	}
	
	@Test
	public void testId() {
		company1 = companyBuilder1.withId(2L).build();
		company2 = companyBuilder2.withId(3L).build();
		assertNotEquals(company1, company2);
		
		company1 = companyBuilder1.withId(1L).build();
		company2 = companyBuilder2.withId(1L).build();
		assertEquals(company1, company2);
	}
	
	@Test
	public void testName() {
		company1 = companyBuilder1.withName(null).build();
		company2 = companyBuilder2.withName(null).build();
		assertEquals(company1, company2);
		
		company1 = companyBuilder1.withName("c1").build();
		company2 = companyBuilder2.withName(null).build();
		assertNotEquals(company1, company2);
		
		company1 = companyBuilder1.withName(null).build();
		company2 = companyBuilder2.withName("c2").build();
		assertNotEquals(company1, company2);
		
		company1 = companyBuilder1.withName("c1").build();
		company2 = companyBuilder2.withName("c2").build();
		assertNotEquals(company1, company2);
		
		company1 = companyBuilder1.withName("c").build();
		company2 = companyBuilder2.withName("c").build();
		assertEquals(company1, company2);
	}
	
}
