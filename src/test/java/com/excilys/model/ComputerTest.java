package com.excilys.model;

import static org.junit.Assert.assertNotEquals;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class ComputerTest extends TestCase {
	
	private static ComputerBuilder computerBuilder1 = new ComputerBuilder();
	private static ComputerBuilder computerBuilder2 = new ComputerBuilder();
	
	static Computer computer1;
	static Computer computer2;
	
	public ComputerTest() {
		super();
	}
	
	@Before
    public void setUp() {
		computer1 = computerBuilder1.empty().build();
		computer2 = computerBuilder2.empty().build();
    }
	
	@Test
    public static void testComputer() {
		assertEquals(computer1, computer1);
		assertNotNull(computer1);
	}
	
	@Test
    public static void testId() {
		computer1 = computerBuilder1.withId(2L).build();
		computer2 = computerBuilder2.withId(3L).build();
		Assert.assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withId(1L).build();
		computer2 = computerBuilder2.withId(1L).build();
		assertEquals(computer1, computer2);
	}
	
	@Test
    public static void testName() {
		computer1 = computerBuilder1.withName(null).build();
		computer2 = computerBuilder2.withName(null).build();
		assertEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withName("c1").build();
		computer2 = computerBuilder2.withName(null).build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withName(null).build();
		computer2 = computerBuilder2.withName("c2").build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withName("c1").build();
		computer2 = computerBuilder2.withName("c2").build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withName("c").build();
		computer2 = computerBuilder2.withName("c").build();
		assertEquals(computer1, computer2);
	}
	
	@Test
    public static void testIntroduced() {
		computer1 = computerBuilder1.withIntroduced(null).build();
		computer2 = computerBuilder2.withIntroduced(null).build();
		assertEquals(computer1, computer2);
		
		Timestamp time1 = Timestamp.valueOf("2000-01-01 00:00:00");
		
		computer1 = computerBuilder1.withIntroduced(null).build();
		computer2 = computerBuilder2.withIntroduced(time1).build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withIntroduced(time1).build();
		computer2 = computerBuilder2.withIntroduced(null).build();
		assertNotEquals(computer1, computer2);
		
		Timestamp time2 = Timestamp.valueOf("2001-01-01 00:00:00");
		
		computer1 = computerBuilder1.withIntroduced(time1).build();
		computer2 = computerBuilder2.withIntroduced(time2).build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withIntroduced(time1).build();
		computer2 = computerBuilder2.withIntroduced(time1).build();
		assertEquals(computer1, computer2);
	}
	
	@Test
    public static void testDiscontinued() {
		computer1 = computerBuilder1.withDiscontinued(null).build();
		computer2 = computerBuilder2.withDiscontinued(null).build();
		assertEquals(computer1, computer2);
		
		Timestamp time1 = Timestamp.valueOf("2000-01-01 00:00:00");
		
		computer1 = computerBuilder1.withDiscontinued(null).build();
		computer2 = computerBuilder2.withDiscontinued(time1).build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withDiscontinued(time1).build();
		computer2 = computerBuilder2.withDiscontinued(null).build();
		assertNotEquals(computer1, computer2);
		
		Timestamp time2 = Timestamp.valueOf("2001-01-01 00:00:00");
		
		computer1 = computerBuilder1.withDiscontinued(time1).build();
		computer2 = computerBuilder2.withDiscontinued(time2).build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withDiscontinued(time1).build();
		computer2 = computerBuilder2.withDiscontinued(time1).build();
		assertEquals(computer1, computer2);
	}
	
	@Test
    public static void testCompany() {
		computer1 = computerBuilder1.withCompany(null).build();
		computer2 = computerBuilder2.withCompany(null).build();
		assertEquals(computer1, computer2);
		
		CompanyBuilder companyBuilder1 = new CompanyBuilder();
		CompanyBuilder companyBuilder2 = new CompanyBuilder();
		Company company1 = companyBuilder1.empty().build();
		Company company2 = companyBuilder2.withId(1L).build();
		
		computer1 = computerBuilder1.withCompany(company1).build();
		computer2 = computerBuilder2.withCompany(company2).build();
		assertNotEquals(computer1, computer2);
		
		computer1 = computerBuilder1.withCompany(company1).build();
		computer2 = computerBuilder2.withCompany(company1).build();
		assertEquals(computer1, computer2);
		
	}

}
