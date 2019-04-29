package com.excilys.model;

public class ComputerTest {

//	private ComputerBuilder computerBuilder1 = new ComputerBuilder();
//	private ComputerBuilder computerBuilder2 = new ComputerBuilder();
//
//	Computer computer1;
//	Computer computer2;
//	Computer computer3;
//
//	public ComputerTest() {
//		super();
//	}
//
//	@Before
//	public void setUp() throws ModelException {
//		computer1 = computerBuilder1.empty().build();
//		computer2 = computerBuilder2.empty().build();
//		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
//		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
//		computer3 = new Computer(0L, "name", introduced, discontinued, null);
//	}
//
//	@Test
//	public void testEmpty() {
//		assertEquals(computer1, computer1);
//		assertNotNull(computer1);
//	}
//
//	@Test
//	public void testId() throws ModelException {
//		computer1 = computerBuilder1.withId(2L).build();
//		computer2 = computerBuilder2.withId(3L).build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withId(1L).build();
//		computer2 = computerBuilder2.withId(1L).build();
//		assertEquals(computer1, computer2);
//	}
//
//	@Test
//	public void testName() throws ModelException {
//		computer1 = computerBuilder1.withName(null).build();
//		computer2 = computerBuilder2.withName(null).build();
//		assertEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withName("c1").build();
//		computer2 = computerBuilder2.withName(null).build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withName(null).build();
//		computer2 = computerBuilder2.withName("c2").build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withName("c1").build();
//		computer2 = computerBuilder2.withName("c2").build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withName("c").build();
//		computer2 = computerBuilder2.withName("c").build();
//		assertEquals(computer1, computer2);
//	}
//
//	@Test
//	public void testIntroduced() throws ModelException {
//		computer1 = computerBuilder1.withIntroduced(null).build();
//		computer2 = computerBuilder2.withIntroduced(null).build();
//		assertEquals(computer1, computer2);
//
//		Timestamp time1 = Timestamp.valueOf("2000-01-01 00:00:00");
//
//		computer1 = computerBuilder1.withIntroduced(null).build();
//		computer2 = computerBuilder2.withIntroduced(time1).build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withIntroduced(time1).build();
//		computer2 = computerBuilder2.withIntroduced(null).build();
//		assertNotEquals(computer1, computer2);
//
//		Timestamp time2 = Timestamp.valueOf("2001-01-01 00:00:00");
//
//		computer1 = computerBuilder1.withIntroduced(time1).build();
//		computer2 = computerBuilder2.withIntroduced(time2).build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withIntroduced(time1).build();
//		computer2 = computerBuilder2.withIntroduced(time1).build();
//		assertEquals(computer1, computer2);
//	}
//
//	@Test
//	public void testDiscontinued() throws ModelException {
//		computer1 = computerBuilder1.withDiscontinued(null).build();
//		computer2 = computerBuilder2.withDiscontinued(null).build();
//		assertEquals(computer1, computer2);
//
//		Timestamp time1 = Timestamp.valueOf("2000-01-01 00:00:00");
//
//		computer1 = computerBuilder1.withDiscontinued(null).build();
//		computer2 = computerBuilder2.withDiscontinued(time1).build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withDiscontinued(time1).build();
//		computer2 = computerBuilder2.withDiscontinued(null).build();
//		assertNotEquals(computer1, computer2);
//
//		Timestamp time2 = Timestamp.valueOf("2001-01-01 00:00:00");
//
//		computer1 = computerBuilder1.withDiscontinued(time1).build();
//		computer2 = computerBuilder2.withDiscontinued(time2).build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withDiscontinued(time2).build();
//		computer2 = computerBuilder2.withDiscontinued(time2).build();
//		assertEquals(computer1, computer2);
//	}
//
//	@Test
//	public void testCompany() throws ModelException {
//		computer1 = computerBuilder1.withCompany(null).build();
//		computer2 = computerBuilder2.withCompany(null).build();
//		assertEquals(computer1, computer2);
//
//		CompanyBuilder companyBuilder1 = new CompanyBuilder();
//		CompanyBuilder companyBuilder2 = new CompanyBuilder();
//		Company company1 = companyBuilder1.empty().build();
//		Company company2 = companyBuilder2.withId(1L).build();
//
//		computer1 = computerBuilder1.withCompany(company1).build();
//		computer2 = computerBuilder2.withCompany(company2).build();
//		assertNotEquals(computer1, computer2);
//
//		computer1 = computerBuilder1.withCompany(company1).build();
//		computer2 = computerBuilder2.withCompany(company1).build();
//		assertEquals(computer1, computer2);
//	}
//
//	@Test
//	public void testBuilder() throws ModelException {
//		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
//		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
//		computer2 = computerBuilder2.withAllArguments(0L, "name", introduced, discontinued, null).build();
//		assertEquals(computer2, computer3);
//	}
//	
//	@Test
//	public void testGetters() throws ModelException {
//		Timestamp introduced = Timestamp.valueOf("2000-01-01 00:00:00");
//		Timestamp discontinued = Timestamp.valueOf("2001-01-01 00:00:00");
//		computer2 = computerBuilder2.withAllArguments(0L, "name", introduced, discontinued, null).build();
//		assertEquals(computer2.getId(), computer3.getId());
//		assertEquals(computer2.getName(), computer3.getName());
//		assertEquals(computer2.getIntroduced(), computer3.getIntroduced());
//		assertEquals(computer2.getDiscontinued(), computer3.getDiscontinued());
//		assertEquals(computer2.getCompany(), computer3.getCompany());
//	}

}
