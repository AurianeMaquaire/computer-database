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

	public static ArrayList<ComputerDTO> listeComputers() {
		ArrayList<Computer> computers = computerDao.listAll();
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (Computer c : computers) {
			computersDTO.add(ComputerMapper.computerToComputerDTO(c));
		}

		return computersDTO;
	}

	public static void createComputer(String name, String introduced, String discontinued, String companyId) {
		Timestamp intro = null;
		if (introduced != null || introduced != "") {
			try {
				intro = TimestampMapper.stringToTimestamp(introduced);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} 

		Timestamp disc = null;
		if (discontinued != null || discontinued != "") {
			try {
				disc = TimestampMapper.stringToTimestamp(discontinued);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} 

		Long companyID ;
		if (companyId != null) {
			companyID = Long.parseLong(companyId);
		} else {
			companyID = 1L;
		}

		Optional<Company> company = companyDao.find(companyID);
		if(company.isPresent()) {
			Computer computer = new Computer(name, intro, disc, company.get());
			computerDao.create(computer);
		}
	}

}
