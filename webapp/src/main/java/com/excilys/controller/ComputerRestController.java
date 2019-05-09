package com.excilys.controller;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.excilys.dto.ComputerDTO;
//import com.excilys.model.Page;
//import com.excilys.service.CompanyService;
//import com.excilys.service.ComputerService;

//@RestController
//public class ComputerRestController {
//	
//	@Autowired
//	ComputerService computerService;
//
//	@Autowired
//	CompanyService companyService;
//	
//	@Autowired
//	MessageSource messageSource;
//	
//	@GetMapping({"/", "/Dashboard", "/dashboard"})
//	public List<ComputerDTO> getAll(Model model, 
//			@RequestParam(name="currentPage", required=false) String currentPage, 
//			@RequestParam(name="search", required=false) String search, 
//			@RequestParam(name="sortBy", required=false) String sortBy, 
//			Locale locale) {
//
//		List<ComputerDTO> listComputers = computerService.listeComputers();
//		
//		Page<ComputerDTO> page = new Page<ComputerDTO>(listComputers);
//
//		if (currentPage != null && currentPage != "") {
//			int currentPageInt = Integer.parseInt(currentPage);
//			if (currentPageInt < page.getMaxPages() && currentPageInt >= 0) {
//				page.setCurrentPage(Integer.valueOf(currentPage));
//			} else {
//				String exception = messageSource.getMessage("exceptionPage", null, locale);
//				model.addAttribute("exception", exception);
//				return new ArrayList<ComputerDTO>();
//			}
//		} else {
//			page.setCurrentPage(0);
//		}
//		
//		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
//		if (search != null && search != "") {
//			computers = computerService.searchComputers(search);
//			return computers;
//		}
//
//		List<ComputerDTO> computersSorted = new ArrayList<ComputerDTO>();
//		if (sortBy != null && sortBy != "") {
//			computersSorted = computerService.orderComputers(sortBy);
//			return computersSorted;
//		} 
//		
//  	model.addAttribute("page", page);
//		
//		return listComputers;
//	}
//	
//}
