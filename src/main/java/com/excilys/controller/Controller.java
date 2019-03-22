package com.excilys.controller;

import java.util.ArrayList;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.model.Computer;
import com.excilys.view.View;

public class Controller {
	
	static ComputerDAO computerDao = new ComputerDAO();
	static CompanyDAO companyDao = new CompanyDAO();
	
	public Controller() {
		new View();
	}
	
	/**
	 * Renvoie la liste des ordinateurs
	 */
	public static ArrayList<Computer> listeComputers() {
		ArrayList<Computer> computers = computerDao.listAll();
		return computers;
	}
	
	/**
	 * Compte le nombre d'ordinateurs
	 */
	public static Long compterOrdinateurs() {	
		Long length = computerDao.length();
		return length;
	}
	
	/**
	 * Compte le nombre de compagnies
	 */
	public static Long compterCompagnies() {
		Long length = companyDao.length();
		return length;
	}
}
