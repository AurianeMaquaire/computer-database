package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.model.CompanyBuilder;

public class CompanyMapper {
	
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
	
	public static Company resultSetToCompany (ResultSet resultSet) {
		
		CompanyBuilder companyBuilder = new CompanyBuilder();
		Company company = companyBuilder.empty().build();
		
		try {
			Long id = resultSet.getLong(1);
			String name = resultSet.getString(2);
			company = companyBuilder.withId(id).withName(name).build();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		}
		
		return company;
	}
	
}
