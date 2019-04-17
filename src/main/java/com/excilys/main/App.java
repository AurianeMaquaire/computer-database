package com.excilys.main;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.controller.ControllerCli;
import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.exception.DAOException;

public class App {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	/**
	 * Lancement du programme
	 */
	public static void main(String[] args) {
				
		logger.trace("Main started");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);
		
		try {
			new ControllerCli(context.getBean(ComputerDAO.class), context.getBean(CompanyDAO.class));
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			logger.error("Main crashed");
		}
		
		context.close();
	}

}
