package com.excilys.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ConnectionDB {
	
	private Connection conn;
	
	public ConnectionDB () throws ClassNotFoundException, SQLException 
	{
		//Chargement du driver mysql et connexion à la base
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db", 
											"admincdb","qwerty1234");
	}
	
	@Override
	protected void finalize()
	{
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Computer> listComputers() {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		try {
			Statement statement = conn.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT * FROM computer");
						
			while (res.next()) {
				Computer comp = new Computer(res.getLong("id"), res.getString("name"),
						res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
						res.getLong("company_id"));
				computers.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computers;
	}
	
	public ArrayList<Company> listCompanies() {
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			Statement statement = conn.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT * FROM company");
						
			while (res.next()) {
				Company comp = new Company(res.getLong("id"), res.getString("name"));
				companies.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
	
	public Computer showComputer(int id) {
		Computer computer = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT * FROM computer WHERE id = " + id);
						
			res.next();
			Computer comp = new Computer(res.getLong("id"), res.getString("name"),
					res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
					res.getLong("company_id"));
			computer = comp;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}
	
	public void createComputer(Long id, String name, Timestamp introduced, Timestamp discontinued, Long company_id) {
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO computer "
					+ "VALUES ('?', '?', '?', '?', '?')");
			statement.setLong(1, id);
			statement.setString(2, name);
			statement.setTimestamp(3, introduced);
			statement.setTimestamp(4, discontinued);
			statement.setLong(5, company_id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateComputer(Long id, String name, Timestamp introduced, Timestamp discontinued, Long company_id) {
		try {
			PreparedStatement statement = conn.prepareStatement("UPDATE computer "
					+ "SET name='?', introduced='?', discontinued='?', company_id='?' "
					+ "WHERE id = '?'");
			statement.setString(1, name);
			statement.setTimestamp(2, introduced);
			statement.setTimestamp(3, discontinued);
			statement.setLong(4, company_id);
			statement.setLong(5, id);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteComputer(String name) {
		try {
			PreparedStatement statement = conn.prepareStatement("DELETE FROM computer "
					+ "WHERE name = '?'");
			statement.setString(1, name);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}