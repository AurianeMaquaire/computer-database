package com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.DAOException;
import com.excilys.model.Page;
import com.excilys.service.ComputerService;

@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	private Page<ComputerDTO> page;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		ArrayList<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
		try {
			listComputers = ComputerService.listeComputers();
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			logger.error("Dashboard Servlet", e);
			throw new ServletException("Dashboard Servlet Exception");
		}
		
		this.page = null;
		if (request.getAttribute("page") == null) {
			this.page = new Page<ComputerDTO>(listComputers);
		} else {
			this.page = (Page<ComputerDTO>) request.getAttribute("page");
		}

		page.setData(listComputers);

		String currentPage = request.getParameter("currentPage");
		if (currentPage != null && currentPage != "") {
			page.setCurrentPage(Integer.valueOf(currentPage));
		} else {
			page.setCurrentPage(0);
		}
		
		String search = request.getParameter("search");
		ArrayList<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		if (search != null && search != "") {
			try {
				computers = ComputerService.searchComputers(search);
			} catch (SQLException | DAOException e) {
				e.printStackTrace();
				logger.error("Dashboard Servlet", e);
				throw new ServletException("Dashboard Servlet Exception");
			}
			page.setData(computers);
		} else {
			page.setData(listComputers);
		}
		
		String sortBy = request.getParameter("sortBy");
		ArrayList<ComputerDTO> computersSorted = new ArrayList<ComputerDTO>();
		if (sortBy != null && sortBy != "") {
			try {
				computersSorted = ComputerService.orderComputers(sortBy);
			} catch (SQLException | DAOException e) {
				e.printStackTrace();
				logger.error("Dashboard Servlet", e);
				throw new ServletException("Dashboard Servlet Exception");
			}
			page.setData(computersSorted);
		}
		
		request.setAttribute("page", page);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("static/views/dashboard.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		String[] computersToDelete = request.getParameterValues("cb");

		if (computersToDelete != null) {
			for (String id : computersToDelete) {
				try {
					ComputerService.deleteComputer(id);
				} catch (SQLException | DAOException e) {
					e.printStackTrace();
					logger.error("Dashboard Servlet", e);
					throw new ServletException("Dashboard Servlet Exception");
				}
			}
		} 
		
		response.sendRedirect("Dashboard");
	}

}
