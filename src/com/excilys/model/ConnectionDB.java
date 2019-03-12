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
	
	public ConnectionDB () throws ClassNotFoundException, SQLException {
		//Chargement du driver mysql et connexion à la base
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db", 
											"admincdb","qwerty1234");
	}
	
	public Connection getConn () {
		return conn;
	}
	
	@Override
	protected void finalize () {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Renvoie la liste des ordinateurs
	 * @return ArrayList<Computer>
	 */
	public ArrayList<Computer> listComputers () {
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
	
	/**
	 * Renvoie la liste des compagnies
	 * @return ArrayList<Company>
	 */
	public ArrayList<Company> listCompanies () {
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
	
	/**
	 * Renvoie les informations détaillées sur un ordinateur
	 * @param id l'identifiant de l'ordinateur
	 * @return un ordinateur
	 */
	public Computer showComputer (int id) {
		Computer computer = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT * FROM computer WHERE id = " + id);
						
			if (res.next()) {
				Computer comp = new Computer(res.getLong("id"), res.getString("name"),
						res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
						res.getLong("company_id"));
				computer = comp;
			} else {
				System.out.println("This computer doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}
	
	/**
	 * Crée un ordianteur dans la base de données
	 * @param name le nom de l'ordinateur
	 * @param introduced la date à laquelle il a été mit sur le marché
	 * @param discontinued la date à laquelle il a été enlevé du marché
	 * @param company_id l'identifiant de la compagnie
	 */
	public void createComputer (String name, Timestamp introduced, Timestamp discontinued, Long company_id) {
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO computer "
					+ "(name, introduced, discontinued, company_id)"
					+ "VALUES ('?', '?', '?', '?')");
			statement.setString(1, name);
			statement.setTimestamp(2, introduced);
			statement.setTimestamp(3, discontinued);
			statement.setLong(4, company_id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Met à jour un ordinateur dans la base de données
	 * @param id l'identifiant de l'ordinateur
	 * @param name le nom de l'ordinateur
	 * @param introduced la date à laquelle il a été mit sur le marché
	 * @param discontinued la date à laquelle il a été enlevé du marché
	 * @param company_id l'identifiant de la compagnie
	 */
	public void updateComputer (Long id, String name, Timestamp introduced, Timestamp discontinued, Long company_id) {
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
	
	/**
	 * Supprime un ordinateur de la base de données
	 * @param id l'identifiant de l'ordinateur
	 */
	public void deleteComputer (Long id) {
		try {
			PreparedStatement statement = conn.prepareStatement("DELETE FROM computer "
					+ "WHERE id = '?'");
			statement.setLong(1, id);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}