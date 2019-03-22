package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.controller.Controller;
import com.excilys.dto.ComputerDTO;
import com.excilys.service.ComputerService;

@WebServlet("/Dashboard")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		Long nbComputers = Controller.compterOrdinateurs();
		request.setAttribute("nbComputers", nbComputers);
		
		ArrayList<ComputerDTO> listComputers = ComputerService.listeComputers();
		request.setAttribute("listComputers", listComputers);
		
		
		/*
		<%
		long nbComputers = (long) request.getAttribute("nbComputers");
		out.print(nbComputers + " computers found");
		%>
		<!-- <c:out value = ${nbComputers} /> -->
		<!-- <c:out value="121 Computers found" /> -->
		 */
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("static/views/dashboard.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		doGet(request, response) ;
	}

}
