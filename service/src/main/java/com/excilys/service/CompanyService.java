package com.excilys.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.CompanyDAO;
import com.excilys.dto.CompanyDTO;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

@Service
public class CompanyService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	CompanyDAO companyDao;
	
	public CompanyService() {
		super();
	}
	
	public List<CompanyDTO> listeCompagnies() {
		logger.debug("Fonction listeCompagnies dans CompanyService");
		List<Company> companies = companyDao.listAll();
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		
		for (Company c : companies) {
			companiesDTO.add(CompanyMapper.companyToCompanyDTO(c));
		}
		
		return companiesDTO;
	}

}
