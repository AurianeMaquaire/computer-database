package com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.exception.DAOException;
import com.excilys.exception.ValidatorException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(EditComputerServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		ArrayList<CompanyDTO> listCompanies = new ArrayList<CompanyDTO>();
		try {
			listCompanies = CompanyService.listeCompagnies();
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			logger.error("Edit Computer Servlet", e);
			throw new ServletException("Edit Computer Servlet Exception");
		} 
		request.setAttribute("listCompanies", listCompanies);

		String id = request.getParameter("computerId");

		Optional<ComputerDTO> computer = Optional.empty();
		try {
			computer = ComputerService.findComputer(id);
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			logger.error("Add Computer Servlet", e);
			throw new ServletException("Add Computer Servlet Exception");
		} 
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

		try {
			ComputerService.editComputer(id, name, introduced, discontinued, companyId);
			response.sendRedirect("Dashboard");
		} catch (ValidatorException | SQLException | DAOException e) {
			e.getMessage();
			e.printStackTrace();
			request.setAttribute("exception", e.getMessage());
			doGet(request, response);
			logger.error("Add Computer Servlet", e);
			throw new ServletException("Add Computer Servlet Exception");
		} 
	}

}
