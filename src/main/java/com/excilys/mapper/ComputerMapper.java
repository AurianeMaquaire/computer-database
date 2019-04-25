package com.excilys.mapper;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.dao.CompanyDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;

@Component
public class ComputerMapper {
	
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

}
