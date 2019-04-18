package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@Controller
public class EditComputerController {

	@Autowired
	ComputerService computerService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	MessageSource messageSource;

	@GetMapping({"/EditComputer", "/editcomputer"})
	public String getEditComputer(Model model, 
			@RequestParam(name="computerId", required=true) String id) 
					throws ServletException, IOException {
		
		ArrayList<CompanyDTO> listCompanies = new ArrayList<CompanyDTO>();
		try {
			listCompanies = companyService.listeCompagnies();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Edit Computer Exception");
		} 
		model.addAttribute("listCompanies", listCompanies);

		Optional<ComputerDTO> computer = Optional.empty();
		try {
			computer = computerService.findComputer(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Edit Computer Exception");
		} 
		if (computer.isPresent()) {
			model.addAttribute("computer", computer.get());
		}

		return "editComputer";
	}

	@PostMapping({"/EditComputer", "/editcomputer"})
	public String postEditComputer(Model model, 
			@RequestParam(name="computerId", required=false) String id, 
			@RequestParam(name="computerName", required=false) String name, 
			@RequestParam(name="introduced", required=false) String introduced, 
			@RequestParam(name="discontinued", required=false) String discontinued, 
			@RequestParam(name="companyId", required=false) String companyId, 
			Locale locale) 
					throws ServletException, IOException {
		
		try {
			computerService.editComputer(id, name, introduced, discontinued, companyId);
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
			return getEditComputer(model, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return getEditComputer(model, id);
		} 
	}

}
