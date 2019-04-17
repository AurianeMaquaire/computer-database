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

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Page;
import com.excilys.service.ComputerService;

@Controller
public class DashboardController {

	@Autowired
	ComputerService computerService;

	@GetMapping({"/", "/Dashboard", "/dashboard"})
	public String getDashboard(Model model, 
			@RequestParam(name="page", required=false) Page<ComputerDTO> page, 
			@RequestParam(name="currentPage", required=false) String currentPage, 
			@RequestParam(name="search", required=false) String search, 
			@RequestParam(name="sortBy", required=false) String sortBy) 
					throws ServletException, IOException {

		ArrayList<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
		try {
			listComputers = computerService.listeComputers();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Dashboard Exception");
		}

		if (page == null) {
			page = new Page<ComputerDTO>(listComputers);
		} 

		page.setData(listComputers);

		if (currentPage != null && currentPage != "") {
			page.setCurrentPage(Integer.valueOf(currentPage));
		} else {
			page.setCurrentPage(0);
		}

		ArrayList<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		if (search != null && search != "") {
			try {
				computers = computerService.searchComputers(search);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException("Dashboard Exception");
			}
			page.setData(computers);
		} else {
			page.setData(listComputers);
		}

		ArrayList<ComputerDTO> computersSorted = new ArrayList<ComputerDTO>();
		if (sortBy != null && sortBy != "") {
			try {
				computersSorted = computerService.orderComputers(sortBy);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ServletException("Dashboard Exception");
			}
			page.setData(computersSorted);
		}

		model.addAttribute("page", page);

		return "dashboard";
	}

	@PostMapping({"/", "/Dashboard", "/dashboard"})
	public String postDashboard(Model model, 
			@RequestParam(name="cb", required=true) String[] computersToDelete) 
					throws ServletException, IOException {

		if (computersToDelete != null) {
			for (String id : computersToDelete) {
				try {
					computerService.deleteComputer(id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServletException("Dashboard Exception");
				}
			}
		} 
		return "redirect:" + "/Dashboard";
	}

}
