package com.excilys.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionDAO.class);

	private static ConnectionDAO instance = null;

	public static final String DAO_PROPERTIES = "datasource.properties";

	private static HikariDataSource hikariDataSource;

	private ConnectionDAO() throws DAOException {
		this(DAO_PROPERTIES);
	}

	private ConnectionDAO(String propertiesPath) throws DAOException {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(propertiesPath);
		try {
			properties.load(fichierProperties);
			HikariConfig hikariConfig = new HikariConfig(properties);
			hikariDataSource = new HikariDataSource(hikariConfig);
		} catch (IOException e) {
			logger.error("Exception dans ConnectionDAO", e.getMessage());
			throw new DAOException("Erreur dans le constructeur de ConnectionDAO");
		}
	}

	public static ConnectionDAO getInstance() throws DAOException {
		if (instance == null) {
			try {
				instance = new ConnectionDAO();
			} catch (DAOException e) {
				logger.error("Exception dans ConnectionDAO", e.getMessage());
				throw new DAOException("Erreur dans la fonction getInstance() de ConnectionDAO");
			}
		}
		return instance;
	}

	public void close() {
		if (instance != null) {
			hikariDataSource.close();
		}		
	}

}
