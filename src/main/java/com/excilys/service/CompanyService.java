package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.CompanyDAO;
import com.excilys.dto.CompanyDTO;
import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

@Service
public class CompanyService {
	
	@Autowired
	CompanyDAO companyDao;
	
	public CompanyService() {
		super();
	}
	
	public ArrayList<CompanyDTO> listeCompagnies() throws SQLException, DAOException {
		ArrayList<Company> companies = companyDao.listAll();
		ArrayList<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		
		for (Company c : companies) {
			companiesDTO.add(CompanyMapper.companyToCompanyDTO(c));
		}
		
		return companiesDTO;
	}

}
