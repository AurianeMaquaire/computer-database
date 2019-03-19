package com.excilys.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

public class CompanyDAOTest {
	
	@Mock
	ResultSet rs;
	
	@BeforeEach
	public void setUp() throws SQLException {
		Mockito.when(rs.getLong("companyId")).thenReturn(1L);
		Mockito.when(rs.getString("companyName")).thenReturn("Company name");
	}
	
	@Test
	public void testCompany() throws SQLException {
		Company company = CompanyMapper.resultSetToCompany(rs);
		
		assertEquals(company.getId(), 1L);
		assertEquals(company.getName(),"Company name");
			
	}
	
}
