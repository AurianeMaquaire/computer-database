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
			if (resultSet.next()) {
				Long id = resultSet.getLong("computerId");
				String name = resultSet.getString("computerName");
				Timestamp introduced = resultSet.getTimestamp("computerIntroduced");
				Timestamp discontinued = resultSet.getTimestamp("computerDiscontinued");
				Long companyId = resultSet.getLong("companyId");
				String companyName = resultSet.getString("companyName");
				Company company = new Company(companyId, companyName);
				computer = computerBuilder.withId(id)
						.withName(name)
						.withIntroduced(introduced)
						.withDiscontinued(discontinued)
						.withCompany(company)
						.build();
			}

		} catch (SQLException e) {
			e.getCause().printStackTrace();
			logger.error("SQLException", e);
		}

		return computer;
	}

}
