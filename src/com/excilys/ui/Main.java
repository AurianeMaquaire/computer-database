package com.excilys.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.controller.Controller;

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		logger.trace("Main started");
		
		new Controller();
		
	}
	
}
