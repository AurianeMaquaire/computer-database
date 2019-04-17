package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping({"/AddComputer", "/addcomputer"})
	public String getAddComputer(Model model) throws ServletException, IOException {

		ArrayList<CompanyDTO> listCompanies = new ArrayList<CompanyDTO>();
		try {
			listCompanies = companyService.listeCompagnies();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Add Computer Exception");
		}
		model.addAttribute("listCompanies", listCompanies);

		return "addComputer";
	}

	@PostMapping({"/AddComputer", "/addcomputer"})
	public String postAddComputer(Model model, 
			@RequestParam(name="computerName", required=true) String name, 
			@RequestParam(name="introduced", required=false) String introduced, 
			@RequestParam(name="discontinued", required=false) String discontinued, 
			@RequestParam(name="companyId", required=false) String companyId) 
					throws ServletException, IOException {

		try {
			computerService.createComputer(name, introduced, discontinued, companyId);
			return "redirect:/Dashboard";
		} catch (ValidatorException | SQLException e) {
			e.printStackTrace();
			model.addAttribute("exception", e.getMessage());
			return getAddComputer(model);
		}
	}

}
