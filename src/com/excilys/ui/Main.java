package com.excilys.ui;

import java.sql.SQLException;

import com.excilys.model.ConnectionDB;

public class Main {
	
	public static void main(String[] args) {
		//System.out.println("Hello");
		
		try {
			ConnectionDB conn = new ConnectionDB();
			/*
			ArrayList<Computer> computers = new ArrayList<Computer>();
			computers = conn.listComputers();
			System.out.println("Liste des ordinateurs :");
			for(Computer c : computers) {
				System.out.println(c);
			}
			
			ArrayList<Company> companies = new ArrayList<Company>();
			companies = conn.listCompanies();
			System.out.println("Liste des compagnies :");
			for(Company c : companies) {
				System.out.println(c);
			}
			
			Computer computer = conn.showComputer(1);
			System.out.println("Ordinateur 1 :");
			System.out.println(computer);
			*/
			
			//ComputerFactory cf = new ComputerFactory();
			//Computer c = cf.creerComputer();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
