package com.excilys.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.model.ConnectionDB;

public class Main {
	
	public static void main(String[] args) {
		//System.out.println("Hello");
		try {
			ConnectionDB conn = new ConnectionDB();
			/*
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery("SELECT COUNT(*) FROM computer");
			int nbComputers;
			/*
			while (res.next()) {
				//nbComputers;
			}
			*/
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
}
