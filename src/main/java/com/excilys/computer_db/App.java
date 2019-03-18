package com.excilys.computer_db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.controller.Controller;

public class App {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	/**
	 * Lancement du programme
	 */
	public static void main(String[] args) {
		
		logger.trace("Main started");
		
		new Controller();
		
	}
	
}
