package com.excilys.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PageTest<T> {
	
	Page<Computer> page;
	
	@Before
	public void setUp() {
		page = new Page<Computer>(new ArrayList<Computer>());
	}
	
	@Test
	public void testCurrentPage() {
		page.setCurrentPage(42);
		assertEquals(page.getCurrentPage(), 0);
	}
	
}
