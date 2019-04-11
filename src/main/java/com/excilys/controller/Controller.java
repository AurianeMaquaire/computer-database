package com.excilys.controller;

import java.sql.SQLException;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.exception.DAOException;
import com.excilys.view.View;

public class Controller {
	
	ComputerDAO computerDao;
	CompanyDAO companyDao;
	
	public Controller(ComputerDAO computerDao, CompanyDAO companyDao) throws SQLException, DAOException {
		this.computerDao = computerDao;
		this.companyDao = companyDao;
		new View(this.computerDao, this.companyDao);
	}
	
}
