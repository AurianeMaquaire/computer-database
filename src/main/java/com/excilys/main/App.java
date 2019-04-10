package com.excilys.main;

import com.excilys.controller.Controller;
import com.excilys.exception.DAOException;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	/**
	 * Lancement du programme
	 */
	public static void main(String[] args) {
		
		logger.trace("Main started");
		
		try {
			new Controller();
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			logger.error("Main crashed");
		}
		
	}

}
