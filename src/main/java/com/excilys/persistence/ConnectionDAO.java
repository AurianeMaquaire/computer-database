package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.CompanyDAO;
import com.excilys.exception.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class ConnectionDAO {
/*
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true" + 
			"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + 
			"serverTimezone=UTC";
	//private static String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private static String user = "admincdb";
	private static String password = "qwerty1234";
	private static String driver = "com.mysql.cj.jdbc.Driver";

	private static HikariConfig config;
	private static HikariDataSource datasource;

	private static Connection connect;

	private static final String PROPERTIES = "ressources/datasource.properties";

	
	public static Connection getInstance(){
		if(connect == null){
			try {
				Class.forName(driver);
				connect = DriverManager.getConnection(url, user, password);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	
		return connect;	
	}

	public ConnectionDAO () {}

	public static Connection getInstance() throws SQLException {
		if (connect == null) {
			try {
				config = new HikariConfig(PROPERTIES);
				datasource = new HikariDataSource(config);
				connect = datasource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return connect;	
	}

	public static void closeInstance(){
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
*/
	private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);


	private static ConnectionDAO instance = null;

	public static final String DAO_PROPERTIES = "datasource.properties";

	private HikariDataSource hikariDataSource;
	
	private ConnectionDAO() throws IOException, DAOException {
		this(DAO_PROPERTIES);
	}

	private ConnectionDAO(String propertiesPath) throws IOException, DAOException {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(propertiesPath);
		try {
			properties.load(fichierProperties);
			HikariConfig hikariConfig = new HikariConfig(properties);
			this.hikariDataSource = new HikariDataSource(hikariConfig);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception dans ConnectionDAO", e);
			throw new DAOException("Erreur dans le constructeur de ConnectionDAO");
		}
	}

	public static ConnectionDAO getInstance() throws DAOException {
		if (instance == null) {
			try {
				instance = new ConnectionDAO();
			} catch (IOException | DAOException e) {
				e.printStackTrace();
				logger.error("Exception dans ConnectionDAO", e);
				throw new DAOException("Erreur dans la fonction getInstance() de ConnectionDAO");
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return hikariDataSource.getConnection();
	}
	
}
