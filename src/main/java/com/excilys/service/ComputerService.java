package com.excilys.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.mapper.TimestampMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.validator.ComputerValidator;

public class ComputerService {

	static ComputerDAO computerDao = new ComputerDAO();
	static CompanyDAO companyDao = new CompanyDAO();

	public ComputerService () {
		super();
	}

	public static Optional<ComputerDTO> findComputer (String id) {
		Long computerId = 0L;
		if (id != null || id != "") {
			computerId = Long.parseLong(id);
		}
		Optional<Computer> computer = computerDao.find(computerId);
		Optional<ComputerDTO> computerDTO = Optional.of(ComputerMapper.computerToComputerDTO(computer.get()));
		return computerDTO;
	}

	public static ArrayList<ComputerDTO> listeComputers() {
		ArrayList<Computer> computers = computerDao.listAll();
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (Computer computer : computers) {
			computersDTO.add(ComputerMapper.computerToComputerDTO(computer));
		}

		return computersDTO;
	}

	public static void createComputer(String name, String introducedString, String discontinuedString, String companyId) throws ValidatorException {
		Optional<Timestamp> intro = Optional.empty();
		Timestamp introduced = null;
		if (introducedString != null) {
			intro = TimestampMapper.stringToTimestamp(introducedString);
			if (intro.isPresent()) {
				introduced = intro.get();
			}
		} 

		Optional<Timestamp> disc = Optional.empty();
		Timestamp discontined = null;
		if (discontinuedString != null) {
			disc = TimestampMapper.stringToTimestamp(discontinuedString);
			if (disc.isPresent()) {
				discontined = disc.get();
			}
		} 

		Long companyID = null;
		if (companyId != null) {
			companyID = Long.parseLong(companyId);
		} 

		Optional<Company> company = companyDao.find(companyID);
		Computer computer;
		if (company.isPresent()) {
			computer = new Computer(name, introduced, discontined, company.get());
		} else {
			computer = new Computer(name, introduced, discontined, null);
		}
		ComputerValidator.verify(computer);
		computerDao.create(computer);
	}

	public static void editComputer(String id, String name, String introducedString, String discontinuedString, String companyId) throws ValidatorException {
		Long idComputer = 0L;
		if (id != null) {
			idComputer = Long.parseLong(id);
		}

		Optional<Timestamp> intro = Optional.empty();
		Timestamp introduced = null;
		if (introducedString != null) {
			intro = TimestampMapper.stringToTimestamp(introducedString);
			if (intro.isPresent()) {
				introduced = intro.get();
			}
		} 

		Optional<Timestamp> disc = Optional.empty();
		Timestamp discontined = null;
		if (discontinuedString != null) {
			disc = TimestampMapper.stringToTimestamp(discontinuedString);
			if (disc.isPresent()) {
				discontined = disc.get();
			}
		} 

		Long companyID = null;
		if (companyId != null) {
			companyID = Long.parseLong(companyId);
		} 

		Optional<Company> company = companyDao.find(companyID);
		Computer computer;
		if (company.isPresent()) {
			computer = new Computer(idComputer, name, introduced, discontined, company.get());
		} else {
			computer = new Computer(idComputer, name, introduced, discontined, null);
		}
		ComputerValidator.verify(computer);
		computerDao.update(computer);
	}

	public static void deleteComputer(String id) {
		Long idComputer = 0L;
		if (id != null && id != "") {
			idComputer = Long.parseLong(id);
		}

		Optional<Computer> computer = computerDao.find(idComputer);
		if(computer.isPresent()) {
			computerDao.delete(computer.get());
		}
	}

	public static ArrayList<ComputerDTO> searchComputers(String search) {
		ArrayList<Computer> computers = computerDao.find(search);
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDto = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDto);
		}
		return computersDTO;
	}

	public static ArrayList<ComputerDTO> orderComputers(String sortBy) {
		ArrayList<Computer> computers = computerDao.sort(sortBy);
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDto = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDto);
		}
		return computersDTO;
	}


}
