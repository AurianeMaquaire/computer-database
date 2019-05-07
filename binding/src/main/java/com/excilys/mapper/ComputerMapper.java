package com.excilys.mapper;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.ComputerBuilder;

@Component
public class ComputerMapper {

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
	
	public static Computer computerDTOToComputer(ComputerDTO computerDto) throws ModelException {
		ComputerBuilder computerBuilder = new ComputerBuilder();
		Long id = computerDto.getId();
		String name = computerDto.getName();
		String introducedString = computerDto.getIntroduced();
		String discontinuedString = computerDto.getDiscontinued();

		Long companyId = computerDto.getCompanyId();
		String companyName = computerDto.getCompanyName();
		
		Company company = new Company(companyId, companyName);
		
		Optional<Timestamp> introduced = Optional.empty();
		if (! "".equals(introducedString)) {
			introduced = TimestampMapper.stringToTimestamp(introducedString);
		} 

		Optional<Timestamp> discontinued = Optional.empty();
		if (! "".equals(discontinuedString)) {
			discontinued = TimestampMapper.stringToTimestamp(discontinuedString);
		} 
		
		Computer computer = computerBuilder.withId(id).withName(name).withCompany(company).build();
		
		if (introduced.isPresent()) {
			computer = computerBuilder.withIntroduced(introduced.get()).build();
		}
		if (discontinued.isPresent()) {
			computer = computerBuilder.withDiscontinued(discontinued.get()).build();
		}

		return computer;
	}

}
