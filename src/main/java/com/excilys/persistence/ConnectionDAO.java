package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
	
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private static String user = "admincdb";
	private static String password = "qwerty1234";
	
	private static Connection connect;
	
	/**
	 * Retourne l'instance de connexion
	 * @return la connection
	 */
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}
	
	/**
	 * Ferme l'instance de connexion
	 */
	public static void closeInstance(){
		if(connect != null){
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
}
