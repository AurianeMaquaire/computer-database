package com.excilys.model;

public class ComputerBuilderTest {
	
//	Computer computer1;
//	Computer computer2;
//	ComputerBuilder computerBuilder1;
//	
//	@Before
//	public void setUp() {
//		computer1 = new Computer();
//		computer2 = new Computer();
//		computerBuilder1 = new ComputerBuilder();
//	}
//	
//	@Test
//	public void testEmpty() throws ModelException {
//		computer1 = computerBuilder1.empty().build();
//		computer2 = new Computer(0L, null, null, null, null);
//		assertEquals(computer1, computer2);
//	}
//	
//	@Test
//	public void testId() throws ModelException {
//		computer1 = computerBuilder1.withId(1L).build();
//		computer2 = new Computer(1L, null, null, null, null);
//		assertEquals(computer1, computer2);
//	}
//	
//	@Test
//	public void testName() throws ModelException {
//		computer1 = computerBuilder1.withName("computer").build();
//		computer2 = new Computer(0L, "computer", null, null, null);
//		assertEquals(computer1, computer2);
//	}
//	
//	@Test
//	public void testIntroduced() throws ModelException {
//		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
//		computer1 = computerBuilder1.withIntroduced(introduced).build();
//		computer2 = new Computer(0L, null, introduced, null, null);
//		assertEquals(computer1, computer2);
//	}
//	
//	@Test
//	public void testDiscontinued() throws ModelException {
//		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
//		computer1 = computerBuilder1.withDiscontinued(discontinued).build();
//		computer2 = new Computer(0L, null, null, discontinued, null);
//		assertEquals(computer1, computer2);
//	}
//	
//	@Test
//	public void testCompany() throws ModelException {
//		Company company = new Company();
//		computer1 = computerBuilder1.withCompany(company).build();
//		computer2 = new Computer(0L, null, null, null, company);
//		assertEquals(computer1, computer2);
//	}
//	
//	@Test
//	public void testAllArguments() throws ModelException {
//		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
//		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
//		Company company = new Company();
//		computer1 = computerBuilder1.withAllArguments(1L, "computer", introduced, discontinued, company).build();
//		computer2 = new Computer(1L, "computer", introduced, discontinued, company);
//		assertEquals(computer1, computer2);
//	}
//	
}
