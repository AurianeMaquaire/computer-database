package com.excilys.service;

import java.util.ArrayList;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;

public class ComputerService {
	
	static ComputerDAO computerDao = new ComputerDAO();
	static CompanyDAO companyDao = new CompanyDAO();
	
	public ComputerService () {
		super();
	}
	
	public static ArrayList<ComputerDTO> listeComputers() {
		ArrayList<Computer> computers = computerDao.listAll();
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		
		for (Computer c : computers) {
			computersDTO.add(ComputerMapper.computerToComputerDTO(c));
		}
		
		return computersDTO;
	}
	
}
