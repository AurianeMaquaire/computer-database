package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
