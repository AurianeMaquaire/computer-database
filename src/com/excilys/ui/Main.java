package com.excilys.ui;

import java.sql.SQLException;

import com.excilys.model.ConnectionDB;

public class Main {
	
	public static void main(String[] args) {
		//System.out.println("Hello");
		
		try {
			ConnectionDB conn = new ConnectionDB();
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
