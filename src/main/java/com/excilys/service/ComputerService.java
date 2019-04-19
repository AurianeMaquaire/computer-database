package com.excilys.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.mapper.TimestampMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.validator.ComputerValidator;

@Service
public class ComputerService {
	
	@Autowired 
	ComputerDAO computerDao;
	
	@Autowired
	CompanyDAO companyDao;

	public ComputerService() {
		super();
	}

	public Optional<ComputerDTO> findComputer(String id) throws SQLException {
		Long computerId = 0L;
		if (id != null || id != "") {
			computerId = Long.parseLong(id);
		}
		Optional<Computer> computer = computerDao.find(computerId);
		Optional<ComputerDTO> computerDTO = Optional.empty();
		if (computer.isPresent()) {
			computerDTO = Optional.of(ComputerMapper.computerToComputerDTO(computer.get()));
		}
		return computerDTO;
	}

	public ArrayList<ComputerDTO> listeComputers() throws SQLException {
		List<Computer> computers = computerDao.listAll();
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (Computer computer : computers) {
			computersDTO.add(ComputerMapper.computerToComputerDTO(computer));
		}

		return computersDTO;
	}

	public void createComputer(String name, String introducedString, String discontinuedString, String companyId) throws ValidatorException, SQLException {
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

	public void editComputer(String id, String name, String introducedString, String discontinuedString, String companyId) throws ValidatorException, SQLException {
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

	public void deleteComputer(String id) throws SQLException {
		Long idComputer = 0L;
		if (id != null && id != "") {
			idComputer = Long.parseLong(id);
		}

		Optional<Computer> computer = computerDao.find(idComputer);
		if(computer.isPresent()) {
			computerDao.delete(computer.get());
		}
	}

	public ArrayList<ComputerDTO> searchComputers(String search) throws SQLException {
		List<Computer> computers = computerDao.find(search);
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDto = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDto);
		}
		return computersDTO;
	}

	public ArrayList<ComputerDTO> orderComputers(String sortBy) throws SQLException {
		List<Computer> computers = computerDao.sort(sortBy);
		ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDto = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDto);
		}
		return computersDTO;
	}

}
