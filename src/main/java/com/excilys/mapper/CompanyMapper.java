package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.CompanyBuilder;

@Component 
public class CompanyMapper implements RowMapper<Company> {

	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	public static Optional<Company> resultSetToCompany(ResultSet resultSet) throws DAOException {

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
			throw new DAOException("SQLException dans resultSetToCompany");
		} catch (ModelException e) {
			e.printStackTrace();
			logger.error("ModelException", e);
			throw new DAOException("ModelException dans resultSetToCompany");
		}

		return company;
	}

	public static List<Company> resultSetToListCompany(ResultSet resultSet) throws DAOException {

		List<Company> companies = new ArrayList<Company>();
		CompanyBuilder companyBuilder = new CompanyBuilder();
		Company company;
		try {
			company = companyBuilder.empty().build();
		} catch (ModelException e) {
			e.printStackTrace();
			logger.error("ModelException", e);
			throw new DAOException("ModelException dans resultSetToListCompany");
		}

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
			throw new DAOException("SQLException dans resultSetToListCompany");
		} catch (ModelException e) {
			e.printStackTrace();
			logger.error("ModelException", e);
			throw new DAOException("ModelException dans resultSetToListCompany");
		}

		return companies;
	}

	public static CompanyDTO companyToCompanyDTO(Company company) {
		long id = company.getId();
		String name = company.getName();

		return new CompanyDTO(id, name);
	}

	public Company rsToCompany(ResultSet resultSet) throws DAOException {

		CompanyBuilder companyBuilder = new CompanyBuilder();
		Company company = null;

		try {
			Long id = resultSet.getLong(1);
			String name = resultSet.getString(2);
			company = companyBuilder.withId(id).withName(name).build();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
			throw new DAOException("SQLException dans rsToCompany");
		} catch (ModelException e) {
			e.printStackTrace();
			logger.error("ModelException", e);
			throw new DAOException("ModelException dans rsToCompany");
		}

		return company;
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			return rsToCompany(rs);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
