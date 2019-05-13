package com.excilys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ModelException;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@RestController
@RequestMapping(path = "/computers", produces = "application/json")
public class ComputerRestController {

	@Autowired
	ComputerService computerService;

	@Autowired
	CompanyService companyService;

	@GetMapping
	public List<ComputerDTO> getAll(
			@RequestParam(name="page", required=false) Page<ComputerDTO> page, 
			@RequestParam(name="currentPage", required=false) String currentPage, 
			@RequestParam(name="search", required=false) String search, 
			@RequestParam(name="sortBy", required=false) String sortBy) {

		List<ComputerDTO> listComputers = computerService.listeComputers();

		if (page == null) {
			page = new Page<>(listComputers);
		}

		page.setData(listComputers);

		if (currentPage != null && currentPage != "") {
			int currentPageInt = Integer.parseInt(currentPage);
			if (currentPageInt < page.getMaxPages() && currentPageInt >= 0) {
				page.setCurrentPage(Integer.valueOf(currentPage));
			} else {
				return new ArrayList<>();
			}
		} else {
			page.setCurrentPage(0);
		}

		List<ComputerDTO> computersSearch;
		if (search != null && search != "") {
			computersSearch = computerService.searchComputers(search);
			return computersSearch;
		}

		List<ComputerDTO> computersSorted;
		if (sortBy != null && sortBy != "") {
			computersSorted = computerService.orderComputers(sortBy);
			return computersSorted;
		}
		return listComputers;
	}

	@PostMapping("/deleteComputer")
	public List<ComputerDTO> deleteComputer(@RequestParam(name="cb", required=true) String[] computersToDelete) {
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		if (computersToDelete != null) {
			for (String id : computersToDelete) {
				computerService.deleteComputer(id);
				Optional<ComputerDTO> computerDto = computerService.findComputer(id);
				if (computerDto.isPresent()) {
					computers.add(computerDto.get());
				}
			}
		} 
		return computers;
	}

	@GetMapping({"/editComputer", "/addComputer"})
	public ComputerDTO getComputer(@RequestParam(name="computerId", required=false, defaultValue="") String id) {

		//List<CompanyDTO> listCompanies = companyService.listeCompagnies(); 
		//model.addAttribute("listCompanies", listCompanies);

		if (id == "") {
			return new ComputerDTO();
		}

		Optional<ComputerDTO> computer = computerService.findComputer(id);

		if (computer.isPresent()) {
			return computer.get();
		} else {
			return new ComputerDTO();
		}
	}

	@PostMapping("/editComputer")
	public ComputerDTO editComputer(@ModelAttribute("computer") ComputerDTO computerDto) {
		try {
			Computer computer = ComputerMapper.computerDTOToComputer(computerDto);
			computerService.editComputer(computer);
			return ComputerMapper.computerToComputerDTO(computer);
		} catch (ValidatorException | ModelException e) {
			String id = Long.toString(computerDto.getId());
			return getComputer(id);
		}
	}

	@PostMapping("/addComputer")
	public ComputerDTO addComputer(@ModelAttribute("computer") ComputerDTO computerDto) {
		try {
			Computer computer = ComputerMapper.computerDTOToComputer(computerDto);
			computerService.createComputer(computer);
			return ComputerMapper.computerToComputerDTO(computer);
		} catch (ValidatorException | ModelException e) {
			return getComputer("");
		} 
	}

}
