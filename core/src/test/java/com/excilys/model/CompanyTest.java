package com.excilys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.excilys.exception.ModelException;

public class CompanyTest {
	
	private CompanyBuilder companyBuilder1 = new CompanyBuilder();
	private CompanyBuilder companyBuilder2 = new CompanyBuilder();
	
	Company company1;
	Company company2;
	Company company3;
	
	public CompanyTest() {
		super();
	}
	
	@Before
    public void setUp() throws ModelException {
		company1 = companyBuilder1.empty().build();
		company2 = companyBuilder2.empty().build();
		company3 = new Company(0L, "name");
    }
	
	
	@Test
	public void testCompany() {
		assertEquals(company1, company1);
		assertNotNull(company1);
		assertNotNull(company2);
		assertNotNull(company3);
	}
	
	@Test
	public void testId() throws ModelException {
		company1 = companyBuilder1.withId(2L).build();
		company2 = companyBuilder2.withId(3L).build();
		assertNotEquals(company1, company2);
		
		company1 = companyBuilder1.withId(1L).build();
		company2 = companyBuilder2.withId(1L).build();
		assertEquals(company1, company2);
	}
	
	@Test
	public void testName() throws ModelException {
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
	
	@Test
	public void testBuilder() throws ModelException {
		company2 = companyBuilder2.withIdAndName(0L, "name").build();
		assertEquals(company2, company3);
	}
	
	@Test
	public void testGetters() throws ModelException {
		company2 = companyBuilder2.withIdAndName(0L, "name").build();
		assertEquals(company2.getId(), company3.getId());
		assertEquals(company2.getName(), company3.getName());
	}
	
}
