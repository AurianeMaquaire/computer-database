package com.excilys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.excilys.dao", "com.excilys.mapper"})
@PropertySource("classpath:datasource.properties")
public class ConfigDAO {
	
	public static final String DAO_PROPERTIES = "datasource.properties";
	
	private static HikariDataSource hikariDataSource;
	
	@Bean
	public DataSource dataSource() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(DAO_PROPERTIES);
		try {
			properties.load(fichierProperties);
			HikariConfig hikariConfig = new HikariConfig(properties);
			hikariDataSource = new HikariDataSource(hikariConfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hikariDataSource;
	}
	
}
