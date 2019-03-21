package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.ComputerBuilder;

public class ComputerMapper {

	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	public static Optional<Computer> resultSetToComputer (ResultSet resultSet) {

		ComputerBuilder computerBuilder = new ComputerBuilder();
		Optional<Computer> computer = Optional.empty();

		try {
			if (resultSet.next()) {
				Long id = resultSet.getLong(1);
				String name = resultSet.getString(2);
				Timestamp introduced = resultSet.getTimestamp(3);
				Timestamp discontinued = resultSet.getTimestamp(4);
				Long companyId = resultSet.getLong(5);
				String companyName = resultSet.getString(6);
				Company company = new Company(companyId, companyName);
				computer = Optional.of(computerBuilder.withId(id)
						.withName(name)
						.withIntroduced(introduced)
						.withDiscontinued(discontinued)
						.withCompany(company)
						.build());
			} 

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		}

		return computer;
	}

	public static ArrayList<Computer> resultSetToListComputer (ResultSet resultSet) {

		ArrayList<Computer> computers = new ArrayList<Computer>();
		ComputerBuilder computerBuilder = new ComputerBuilder();
		Computer computer = computerBuilder.empty().build();

		try {
			while (resultSet.next()) {
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
				computers.add(computer);
			} 

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException", e);
		}

		return computers;
	}
	
	public static ComputerDTO ComputerToComputerDTO (Computer computer) {
		Long id = computer.getId();
		String name = computer.getName();
		Timestamp introduced = computer.getIntroduced();
		Timestamp discontinued = computer.getDiscontinued();
		Long companyId = computer.getCompany().getId();
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String introducedString = f.format(introduced);
	    String discontinuedString = f.format(discontinued);
		
		return new ComputerDTO(id, name, introducedString, discontinuedString, companyId);
	}

}
