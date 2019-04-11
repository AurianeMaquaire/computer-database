package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.ComputerBuilder;

@Component
public class ComputerMapper {

	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

	public static Optional<Computer> resultSetToComputer(ResultSet resultSet) throws DAOException {

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
			throw new DAOException("SQLException dans resultSetToComputer");
		} catch (ModelException e) {
			e.printStackTrace();
			logger.error("ModelException", e);
			throw new DAOException("ModelException dans resultSetToComputer");
		}

		return computer;
	}

	public static ArrayList<Computer> resultSetToListComputer(ResultSet resultSet) throws DAOException {

		ArrayList<Computer> computers = new ArrayList<Computer>();
		ComputerBuilder computerBuilder = new ComputerBuilder();
		Computer computer;
		try {
			computer = computerBuilder.empty().build();
		} catch (ModelException e) {
			e.printStackTrace();
			logger.error("ModelException", e);
			throw new DAOException("ModelException dans resultSetToListComputer");
		}

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
			throw new DAOException("SQLException dans resultSetToListComputer");
		} catch (ModelException e) {
			e.printStackTrace();
			logger.error("ModelException", e);
			throw new DAOException("ModelException dans resultSetToListComputer");
		}
		
		return computers;
	}

	public static ComputerDTO computerToComputerDTO(Computer computer) {
		Long id = computer.getId();
		String name = computer.getName();
		Timestamp introduced = computer.getIntroduced();
		Timestamp discontinued = computer.getDiscontinued();
		Long companyId = computer.getCompany().getId();
		String companyName = computer.getCompany().getName();

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

}
