package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.CompanyDAO;
import com.excilys.exception.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	private static ConnectionDAO instance = null;

	public static final String DAO_PROPERTIES = "datasource.properties";

	private static HikariDataSource hikariDataSource;

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
			hikariDataSource = new HikariDataSource(hikariConfig);
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
	
	public DataSource getDataSource() {
		return hikariDataSource;
	}

	public void close() {
		if (instance != null) {
			hikariDataSource.close();
		}		
	}

}
