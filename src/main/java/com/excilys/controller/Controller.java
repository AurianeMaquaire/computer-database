package com.excilys.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.exception.DAOException;
import com.excilys.model.Computer;
import com.excilys.view.View;

public class Controller {
	
	static ComputerDAO computerDao = new ComputerDAO();
	static CompanyDAO companyDao = new CompanyDAO();
	
	public Controller() throws SQLException, DAOException {
		new View();
	}
	
	/**
	 * Renvoie la liste des ordinateurs
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public static ArrayList<Computer> listeComputers() throws SQLException, DAOException {
		ArrayList<Computer> computers = computerDao.listAll();
		return computers;
	}
	
	/**
	 * Compte le nombre d'ordinateurs
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public static Long compterOrdinateurs() throws SQLException, DAOException {	
		Long length = computerDao.length();
		return length;
	}
	
	/**
	 * Compte le nombre de compagnies
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public static Long compterCompagnies() throws SQLException, DAOException {
		Long length = companyDao.length();
		return length;
	}
}
