package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

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
	public void testCompany() throws SQLException, DAOException {
		Optional<Company> company = CompanyMapper.resultSetToCompany(rs);
		
		//assertEquals(company.get().getId(), Long.valueOf(1L));
		//assertEquals(company.get().getName(),"Company name");
	}
	
}
