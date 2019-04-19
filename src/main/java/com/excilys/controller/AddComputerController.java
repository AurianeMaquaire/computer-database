package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
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

		ArrayList<CompanyDTO> listCompanies = new ArrayList<CompanyDTO>();
		try {
			listCompanies = companyService.listeCompagnies();
		} catch (SQLException e) {
			String exception = messageSource.getMessage("exceptionListCompanies", null, locale);
			model.addAttribute("exception", exception);
			return "404";
		}
		model.addAttribute("listCompanies", listCompanies);

		return "addComputer";
	}

	@PostMapping({"/AddComputer", "/addcomputer"})
	public String postAddComputer(Model model, 
			@RequestParam(name="computerName", required=true) String name, 
			@RequestParam(name="introduced", required=false) String introduced, 
			@RequestParam(name="discontinued", required=false) String discontinued, 
			@RequestParam(name="companyId", required=false) String companyId, 
			Locale locale)
					throws IOException {

		try {
			computerService.createComputer(name, introduced, discontinued, companyId);
			return "redirect:/Dashboard";
		} catch (ValidatorException e) {
			String except = e.getMessage();
			String exception = null;
			if (except == "name") {
				exception = messageSource.getMessage("exceptionName", null, locale);
			} else if (except == "discontinued") {
				exception = messageSource.getMessage("exceptionDiscontinued", null, locale);
			} else if (except == "introduced") {
				exception = messageSource.getMessage("exceptionIntroduced", null, locale);
			}
			model.addAttribute("exception",exception);
			return getAddComputer(model, locale);
		} catch (SQLException e) {
			e.printStackTrace();
			return getAddComputer(model, locale);
		}
	}

}
