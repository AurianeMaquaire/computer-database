package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class EditComputerController {

	@Autowired
	ComputerService computerService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	ComputerMapper computerMapper;
	
	@Autowired
	MessageSource messageSource;

	@GetMapping({"/EditComputer", "/editcomputer"})
	public String getEditComputer(Model model, 
			@RequestParam(name="computerId", required=false, defaultValue="") String id, 
			Locale locale) 
					throws IOException {
		
		List<CompanyDTO> listCompanies = new ArrayList<CompanyDTO>();
		listCompanies = companyService.listeCompagnies(); 
		model.addAttribute("listCompanies", listCompanies);

		Optional<ComputerDTO> computer = Optional.empty();
		try {
			computer = computerService.findComputer(id);
		} catch (SQLException e) {
			String exception = messageSource.getMessage("exceptionFindComputer", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		} 
		if (computer.isPresent()) {
			model.addAttribute("computer", computer.get());
		} else {
			String exception = messageSource.getMessage("exceptionFindComputer", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		}

		return "editComputer";
	}

	@PostMapping({"/EditComputer", "/editcomputer"})
	public String postEditComputer(@ModelAttribute("computer") ComputerDTO computerDto,
			BindingResult result, Model model, Locale locale) throws IOException {

		if (result.hasErrors()) {
            return "404";
        }
		
		try {
			computerService.editComputer(computerDto);
			return "redirect:/Dashboard";
		} catch (ValidatorException e) {
			String exception = messageSource.getMessage(e.getMessage(), null, locale);
			model.addAttribute("exception", exception);
			String id = Long.toString(computerDto.getId());
			return getEditComputer(model, id, locale);
		} catch (SQLException e) {
			e.printStackTrace();
			String id = Long.toString(computerDto.getId());
			return getEditComputer(model, id, locale);
		} 
	}

}
