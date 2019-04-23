package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class AddComputerController {

	@Autowired
	ComputerService computerService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	MessageSource messageSource;

	@GetMapping({"/AddComputer", "/addcomputer"})
	public String getAddComputer(Model model, Locale locale) throws IOException {

		List<CompanyDTO> listCompanies = new ArrayList<CompanyDTO>();
		try {
			listCompanies = companyService.listeCompagnies();
		} catch (SQLException e) {
			String exception = messageSource.getMessage("exceptionListCompanies", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		}
		model.addAttribute("listCompanies", listCompanies);
		
		model.addAttribute("computer", new ComputerDTO());

		return "addComputer";
	}

	@PostMapping({"/AddComputer", "/addcomputer"})
	public String postAddComputer(@ModelAttribute("computer") ComputerDTO computerDto,
			BindingResult result, Model model, Locale locale) throws IOException {
		
		if (result.hasErrors()) {
            return "404";
        }
		
		try {
			computerService.createComputer(computerDto);
			return "redirect:/Dashboard";
		} catch (ValidatorException e) {
			String exception = messageSource.getMessage(e.getMessage(), null, locale);
			model.addAttribute("exception", exception);
			return getAddComputer(model, locale);
		} catch (SQLException e) {
			e.printStackTrace();
			return getAddComputer(model, locale);
		}
	}

}
