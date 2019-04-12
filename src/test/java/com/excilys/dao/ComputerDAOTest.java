package com.excilys.dao;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.excilys.exception.DAOException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;


public class ComputerDAOTest {
	
	@Mock
	ResultSet rs;
	
	@Before
	public void setUp() throws SQLException {
		rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenReturn(true);
		Mockito.when(rs.getLong("computerId")).thenReturn(1L);
		Mockito.when(rs.getString("computerName")).thenReturn("Computer name");
		Mockito.when(rs.getTimestamp("computerIntroduced")).thenReturn(Timestamp.valueOf("2000-01-01 00:00:00"));
		Mockito.when(rs.getTimestamp("computerDiscontinued")).thenReturn(Timestamp.valueOf("2001-01-01 00:00:00"));
		Mockito.when(rs.getLong("companyId")).thenReturn(1L);
		Mockito.when(rs.getString("companyName")).thenReturn("Company name");
	}
	
	@Test
	public void testComputer() throws SQLException, DAOException {
		Optional<Computer> computer = ComputerMapper.resultSetToComputer(rs);
		
//		assertEquals(computer.get().getId(), Long.valueOf(1L));
//		assertEquals(computer.get().getName(),"Computer name");
//		assertEquals(computer.get().getIntroduced(),Timestamp.valueOf("2000-01-01 00:00:00"));
//		assertEquals(computer.get().getDiscontinued(),Timestamp.valueOf("2001-01-01 00:00:00"));
//		assertEquals(computer.get().getCompany(),new Company(1L, "Company name")); 
	}
	
}
