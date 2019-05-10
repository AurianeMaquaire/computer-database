package com.excilys.mapper;

import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

@Component 
public class CompanyMapper {
	
	private CompanyMapper() {}

	public static CompanyDTO companyToCompanyDTO(Company company) {
		long id = company.getId();
		String name = company.getName();

		return new CompanyDTO(id, name);
	}
	
	public static Company companyDTOToCompany(CompanyDTO companyDto) {
		Long id = companyDto.getId();
		String name = companyDto.getName();

		return new Company(id, name);
	}

}
