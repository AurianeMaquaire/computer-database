package com.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.ComputerDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.validator.ComputerValidator;

@Service
public class ComputerService {
		
	@Autowired 
	ComputerDAO computerDao;
		
	public ComputerService() {
		super();
	}

	public Optional<ComputerDTO> findComputer(String id) {
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
	
	public Optional<ComputerDTO> findComputer(Long id) {
		Optional<Computer> computer = computerDao.find(id);
		Optional<ComputerDTO> computerDTO = Optional.empty();
		if (computer.isPresent()) {
			computerDTO = Optional.of(ComputerMapper.computerToComputerDTO(computer.get()));
		}
		return computerDTO;
	}

	public List<ComputerDTO> listeComputers() {
		List<Computer> computers = computerDao.listAll();
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (Computer computer : computers) {
			computersDTO.add(ComputerMapper.computerToComputerDTO(computer));
		}
		return computersDTO;
	}

	public void createComputer(Computer computer) throws ValidatorException {
		ComputerValidator.verify(computer);
		computerDao.create(computer);
	}

	public void editComputer(Computer computer) throws ValidatorException {
		ComputerValidator.verify(computer);
		computerDao.update(computer);
	}

	public void deleteComputer(String id) {
		Long idComputer = 0L;
		if (id != null && id != "") {
			idComputer = Long.parseLong(id);
		}

		Optional<Computer> computer = computerDao.find(idComputer);
		if(computer.isPresent()) {
			computerDao.delete(computer.get());
		}
	}

	public List<ComputerDTO> searchComputers(String search) {
		List<Computer> computers = computerDao.find(search);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDto = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDto);
		}
		return computersDTO;
	}

	public List<ComputerDTO> orderComputers(String sortBy) {
		List<Computer> computers = computerDao.sort(sortBy);
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			ComputerDTO computerDto = ComputerMapper.computerToComputerDTO(computer);
			computersDTO.add(computerDto);
		}
		return computersDTO;
	}

}
