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
			res = statement.executeQuery("SELECT cn.id, cn.name, ct.id, ct.name, "
					+ "ct.introduced, ct.discontinued FROM computer AS ct "
					+ "LEFT JOIN company AS cn ON ct.id = cn.id");
						
			while (res.next()) {
				Company company = new Company(res.getLong("cn.id"), res.getString("cn.name"));
				Computer comp = new Computer(res.getLong("ct.id"), res.getString("ct.name"),
						res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
						company);
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
			res = statement.executeQuery("SELECT company.id, company.name FROM company");
						
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
	public Computer showComputer (Long id) {
		Computer computer = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT cn.id, cn.name, ct.id, ct.name, "
					+ "ct.introduced, ct.discontinued FROM computer AS ct "
					+ "LEFT JOIN company AS cn ON ct.id = cn.id WHERE ct.id = " + id);
						
			if (res.next()) {
				Company company = new Company(res.getLong("cn.id"), res.getString("cn.name"));
				Computer tmp = new Computer(res.getLong("ct.id"), res.getString("ct.name"),
						res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
						company);
				computer = tmp;
			} else {
				System.out.println("This computer doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}
	
	/**
	 * Renvoie les informations sur une compagnie à partir de l'id
	 * @param id l'identifiant de la compagnie
	 * @return une compagnie
	 */
	public Company showCompany (Long id) {
		Company company = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT id, name FROM company "
					+ "WHERE id = '" + id + "'");
						
			if (res.next()) {
				company = new Company(res.getLong("id"), res.getString("name"));
			} else {
				System.out.println("This company doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	
	/**
	 * Renvoie les informations sur une compagnie à partir du nom
	 * @param name le nom de la compagnie
	 * @return une compagnie
	 */
	public Company showCompany (String name) {
		Company company = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT id, name FROM company "
					+ "WHERE name = '" + name + "'");
						
			if (res.next()) {
				company = new Company(res.getLong("id"), res.getString("name"));
			} else {
				System.out.println("This company doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	
	/**
	 * Crée un ordinateur dans la base de données
	 * @param name le nom de l'ordinateur
	 * @param introduced la date à laquelle il a été mit sur le marché
	 * @param discontinued la date à laquelle il a été enlevé du marché
	 * @param company_id l'identifiant de la compagnie
	 */
	public void createComputer (String name, Timestamp introduced, Timestamp discontinued, Company company) {
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO computer "
					+ "(name, introduced, discontinued, company_id)"
					+ "VALUES (?, ?, ?, ?)");
			statement.setString(1, name);
			statement.setTimestamp(2, introduced);
			statement.setTimestamp(3, discontinued);
			statement.setLong(4, company.getId());
			
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
	public void updateComputer (Long id, String name, Timestamp discontinued) {
		try {
			PreparedStatement statement = conn.prepareStatement("UPDATE computer "
					+ "SET name=?, discontinued=? "
					+ "WHERE id = ?");
			statement.setString(1, name);
			statement.setTimestamp(2, discontinued);
			statement.setLong(3, id);

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
					+ "WHERE id = ?");
			statement.setLong(1, id);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}