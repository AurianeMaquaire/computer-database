package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;
import com.excilys.model.CompanyBuilder;

public class CompanyMapper {

	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);


	public static Optional<Company> resultSetToCompany (ResultSet resultSet) {

		CompanyBuilder companyBuilder = new CompanyBuilder();
		Optional<Company> company = Optional.empty();

		try {
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				String name = resultSet.getString(2);
				company = Optional.of(companyBuilder.withId(id).withName(name).build());
			} 

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		}

		return company;
	}
	
	public static ArrayList<Company> resultSetToListCompany (ResultSet resultSet) {
		
		ArrayList<Company> companies = new ArrayList<Company>();
		CompanyBuilder companyBuilder = new CompanyBuilder();
		Company company = companyBuilder.empty().build();

		try {
			while (resultSet.next()) {
				Long id = resultSet.getLong(1);
				String name = resultSet.getString(2);
				company = companyBuilder.withId(id).withName(name).build();
				companies.add(company);
			} 

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		}

		return companies;
	}
	
	public static CompanyDTO companyToCompanyDTO (Company company) {
		Long id = company.getId();
		String name = company.getName();
		
		return new CompanyDTO(id, name);
	}

}
