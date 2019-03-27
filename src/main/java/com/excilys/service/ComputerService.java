package com.excilys.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.mapper.TimestampMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerService {

	static ComputerDAO computerDao = new ComputerDAO();
	static CompanyDAO companyDao = new CompanyDAO();

	public ComputerService () {
		super();
	}
	
	public static Optional<ComputerDTO> findComputer (Long id) {
		Optional<Computer> computer = computerDao.find(id);
		Optional<ComputerDTO> computerDTO = Optional.of(ComputerMapper.computerToComputerDTO(computer.get()));
		return computerDTO;
	}

	public static ArrayList<ComputerDTO> listeComputers() {
		ArrayList<Computer> computers = computerDao.listAll();
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (Computer c : computers) {
			computersDTO.add(ComputerMapper.computerToComputerDTO(c));
		}

		return computersDTO;
	}

	public static void createComputer(String name, String introduced, String discontinued, String companyId) {
		if (name == null) {
			name = "";
		}
		
		Timestamp intro = null;
		if (! "".equals(introduced)) {
			try {
				intro = TimestampMapper.stringToTimestamp(introduced);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} 

		Timestamp disc = null;
		if (! "".equals(discontinued)) {
			try {
				disc = TimestampMapper.stringToTimestamp(discontinued);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} 
		
		Long companyID;
		if (companyId != null) {
			companyID = Long.parseLong(companyId);
		} else {
			companyID = null;
		}

		Optional<Company> company = companyDao.find(companyID);
		if(company.isPresent()) {
			Computer computer = new Computer(name, intro, disc, company.get());
			computerDao.create(computer);
		}
	}
	
	public static void editComputer(String id, String name, String introduced, String discontinued, String companyId) {
		Long idComputer = 0L;
		if (id != null) {
			idComputer = Long.parseLong(id);
		}
		
		if (name == null) {
			name = "";
		}
		
		Timestamp intro = null;
		if (! "".equals(introduced)) {
			try {
				intro = TimestampMapper.stringToTimestamp(introduced);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} 

		Timestamp disc = null;
		if (! "".equals(discontinued)) {
			try {
				disc = TimestampMapper.stringToTimestamp(discontinued);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} 
		
		Long companyID;
		if (companyId != null) {
			companyID = Long.parseLong(companyId);
		} else {
			companyID = null;
		}

		Optional<Company> company = companyDao.find(companyID);
		if(company.isPresent()) {
			Computer computer = new Computer(idComputer, name, intro, disc, company.get());
			computerDao.update(computer);
		}
	}

}
