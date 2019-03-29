package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.CompanyDTO;
import com.excilys.exception.ValidatorException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		ArrayList<CompanyDTO> listCompanies = CompanyService.listeCompagnies();
		request.setAttribute("listCompanies", listCompanies);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("static/views/addComputer.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		
		try {
			ComputerService.createComputer(name, introduced, discontinued, companyId);
			response.sendRedirect("Dashboard");
		} catch (ValidatorException e) {
			e.getMessage();
			e.printStackTrace();
			request.setAttribute("exception", e.getMessage());
			doGet(request, response);
		}
	}
	
}
