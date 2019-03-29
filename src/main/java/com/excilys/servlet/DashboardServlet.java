package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Page;
import com.excilys.service.ComputerService;

@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Page<ComputerDTO> page;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		ArrayList<ComputerDTO> listComputers = ComputerService.listeComputers();

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
			computers = ComputerService.searchComputers(search);
			page.setData(computers);
		} else {
			page.setData(listComputers);
		}
		
		String sortBy = request.getParameter("sortBy");
		ArrayList<ComputerDTO> computersSorted = new ArrayList<ComputerDTO>();
		if (sortBy != null && sortBy != "") {
			computersSorted = ComputerService.orderComputers(sortBy);
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
				ComputerService.deleteComputer(id);
			}
		} 
		
		response.sendRedirect("Dashboard");
	}

}
