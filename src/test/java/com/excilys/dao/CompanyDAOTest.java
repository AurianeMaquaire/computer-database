package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CompanyDAOTest {
	
	@Mock
	ResultSet rs;
	
	@Before
	public void setUp() throws SQLException {
		rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getLong("companyId")).thenReturn(1L);
		Mockito.when(rs.getString("companyName")).thenReturn("Company name");
	}
	
	@Test
	public void testCompany() throws SQLException {
//		Optional<Company> company = CompanyMapper.resultSetToCompany(rs);
//		
//		assertEquals(company.get().getId(), Long.valueOf(1L));
//		assertEquals(company.get().getName(),"Company name");
	}
	
}
