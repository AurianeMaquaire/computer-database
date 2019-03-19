package com.excilys.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;


public class ComputerDAOTest {
	
	@Mock
	ResultSet rs;
	
	@BeforeEach
	public void setUp() throws SQLException {
		Mockito.when(rs.getLong("computerId")).thenReturn(1L);
		Mockito.when(rs.getString("computerName")).thenReturn("Computer name");
		Mockito.when(rs.getTimestamp("computerId")).thenReturn(Timestamp.valueOf("2000-01-01 00:00:00"));
		Mockito.when(rs.getTimestamp("computerId")).thenReturn(Timestamp.valueOf("2001-01-01 00:00:00"));
		Mockito.when(rs.getLong("companyId")).thenReturn(1L);
		Mockito.when(rs.getString("companyName")).thenReturn("Company name");
	}
	
	@Test
	public void testComputer() throws SQLException {
		Computer computer = ComputerMapper.resultSetToComputer(rs);
		
		assertEquals(computer.getId(), 1L);
		assertEquals(computer.getName(),"Computer name");
		assertEquals(computer.getIntroduced(),Timestamp.valueOf("2000-01-01 00:00:00"));
		assertEquals(computer.getDiscontinued(),Timestamp.valueOf("2001-01-01 00:00:00"));
		assertEquals(computer.getCompany(),new Company(1l, "Company name")); 
	}
	
}
