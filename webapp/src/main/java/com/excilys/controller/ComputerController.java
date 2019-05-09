package com.excilys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ModelException;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
@RequestMapping("/computers")
public class ComputerController {

	@Autowired
	ComputerService computerService;

	@Autowired
	CompanyService companyService;

	@Autowired
	MessageSource messageSource;

	@GetMapping
	public String getDashboard(Model model, 
			@RequestParam(name="page", required=false) Page<ComputerDTO> page, 
			@RequestParam(name="currentPage", required=false) String currentPage, 
			@RequestParam(name="search", required=false) String search, 
			@RequestParam(name="sortBy", required=false) String sortBy, 
			Locale locale) {

		List<ComputerDTO> listComputers = computerService.listeComputers();

		if (page == null) {
			page = new Page<ComputerDTO>(listComputers);
		}

		page.setData(listComputers);

		if (currentPage != null && currentPage != "") {
			int currentPageInt = Integer.parseInt(currentPage);
			if (currentPageInt < page.getMaxPages() && currentPageInt >= 0) {
				page.setCurrentPage(Integer.valueOf(currentPage));
			} else {
				String exception = messageSource.getMessage("exceptionPage", null, locale);
				model.addAttribute("exception", exception);
				return "404";
			}
		} else {
			page.setCurrentPage(0);
		}

		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		if (search != null && search != "") {
			computers = computerService.searchComputers(search);
			page.setData(computers);
		}

		List<ComputerDTO> computersSorted = new ArrayList<ComputerDTO>();
		if (sortBy != null && sortBy != "") {
			computersSorted = computerService.orderComputers(sortBy);
			page.setData(computersSorted);
		} 

		model.addAttribute("page", page);

		return "dashboard";
	}

	@PostMapping("/deleteComputer")
	public String postDashboard(Model model, 
			@RequestParam(name="cb", required=true) String[] computersToDelete, 
			Locale locale) {

		if (computersToDelete != null) {
			for (String id : computersToDelete) {
				computerService.deleteComputer(id);
			}
		} 
		return "redirect:/computers";
	}

	@GetMapping("/editComputer")
	public String getEditComputer(@RequestParam(name="computerId", required=false, defaultValue="") String id, 
			Locale locale, Model model) {

		List<CompanyDTO> listCompanies = companyService.listeCompagnies(); 
		model.addAttribute("listCompanies", listCompanies);

		Optional<ComputerDTO> computer = computerService.findComputer(id);

		if (computer.isPresent()) {
			model.addAttribute("computer", computer.get());
		} else {
			String exception = messageSource.getMessage("exceptionFindComputer", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		}

		return "editComputer";
	}

	@PostMapping("/editComputer")
	public String postEditComputer(@ModelAttribute("computer") ComputerDTO computerDto,
			BindingResult result, Model model, Locale locale) {

		if (result.hasErrors()) {
			return "404";
		}

		try {
			Computer computer = ComputerMapper.computerDTOToComputer(computerDto);
			computerService.editComputer(computer);
			return "redirect:/computers";
		} catch (ValidatorException | ModelException e) {
			String exception = messageSource.getMessage(e.getMessage(), null, locale);
			model.addAttribute("exception", exception);
			String id = Long.toString(computerDto.getId());
			return getEditComputer(id, locale, model);
		} 
	}

	@GetMapping("/addComputer")
	public String getAddComputer(Model model, Locale locale) {

		List<CompanyDTO> listCompanies = companyService.listeCompagnies();
		model.addAttribute("listCompanies", listCompanies);

		model.addAttribute("computer", new ComputerDTO());

		return "addComputer";
	}

	@PostMapping("/addComputer")
	public String postAddComputer(@ModelAttribute("computer") ComputerDTO computerDto,
			BindingResult result, Model model, Locale locale) {

		if (result.hasErrors()) {
			return "404";
		}

		try {
			Computer computer = ComputerMapper.computerDTOToComputer(computerDto);
			computerService.createComputer(computer);
			return "redirect:/computers";
		} catch (ValidatorException | ModelException e) {
			String exception = messageSource.getMessage(e.getMessage(), null, locale);
			model.addAttribute("exception", exception);
			return getAddComputer(model, locale);
		} 
	}

}
