package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		ArrayList<CompanyDTO> listCompanies = CompanyService.listeCompagnies();
		request.setAttribute("listCompanies", listCompanies);
		
		String id = request.getParameter("computerId");
		
		Optional<ComputerDTO> computer = ComputerService.findComputer(id);
		if (computer.isPresent()) {
			request.setAttribute("computer", computer.get());
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("static/views/editComputer.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		String id = request.getParameter("computerId");
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		
		ComputerService.editComputer(id, name, introduced, discontinued, companyId);
		
		response.sendRedirect("Dashboard");
	}
	
}
