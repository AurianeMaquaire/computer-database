package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.dao.CompanyDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.ComputerBuilder;

@Component
public class ComputerMapper implements RowMapper<Computer> {

	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
	@Autowired
	CompanyDAO companyDAO;

	public static ComputerDTO computerToComputerDTO(Computer computer) {
		long id = computer.getId();
		String name = computer.getName();
		Timestamp introduced = computer.getIntroduced();
		Timestamp discontinued = computer.getDiscontinued();
		long companyId = 0;
		String companyName = null;
		if (computer.getCompany() != null) {
			companyId = computer.getCompany().getId();
			companyName = computer.getCompany().getName();
		}

		String introducedString = null;
		if (introduced != null) {
			introducedString = TimestampMapper.timestampToString(introduced);
		} 

		String discontinuedString = null;
		if (discontinued != null) {
			discontinuedString = TimestampMapper.timestampToString(discontinued);
		} 

		return new ComputerDTO(id, name, introducedString, discontinuedString, companyId, companyName);
	}

	public Computer resultSetToComputer(ResultSet resultSet) throws DAOException, ModelException {

		ComputerBuilder computerBuilder = new ComputerBuilder();
		Computer computer = computerBuilder.empty().build();

		try {
			Long id = resultSet.getLong(1);
			String name = resultSet.getString(2);
			Timestamp introduced = resultSet.getTimestamp(3);
			Timestamp discontinued = resultSet.getTimestamp(4);
			Long companyId = resultSet.getLong(5);

			Optional<Company> companyOpt = Optional.empty();
			companyOpt = companyDAO.find(companyId);
			Company company = null;
			if (companyOpt.isPresent()) {
				company = companyOpt.get();
			} 

			computer = computerBuilder.withId(id)
					.withName(name)
					.withIntroduced(introduced)
					.withDiscontinued(discontinued)
					.withCompany(company)
					.build();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
			throw new DAOException("SQLException dans rsToComputer");
		} 

		return computer;
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			return resultSetToComputer(rs);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return null;
	}

}
