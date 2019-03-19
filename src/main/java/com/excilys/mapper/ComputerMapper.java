package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.ComputerBuilder;

public class ComputerMapper {

	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	public static Computer resultSetToComputer (ResultSet resultSet) {

		ComputerBuilder computerBuilder = new ComputerBuilder();
		Computer computer = computerBuilder.empty().build();

		try {
			Long id = resultSet.getLong(1);
			String name = resultSet.getString(2);
			Timestamp introduced = resultSet.getTimestamp(3);
			Timestamp discontinued = resultSet.getTimestamp(4);
			Long companyId = resultSet.getLong(5);
			String companyName = resultSet.getString(6);
			Company company = new Company(companyId, companyName);
			computer = computerBuilder.withId(id)
					.withName(name)
					.withIntroduced(introduced)
					.withDiscontinued(discontinued)
					.withCompany(company)
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		}

		return computer;
	}

}
