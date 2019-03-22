package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.ConnectionDAO;

public class CompanyDAO {

	private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	private final String SELECT_ALL = "SELECT id, name FROM company";
	private final String SELECT_ID = SELECT_ALL + " WHERE id = ?";
	private final String SELECT_NAME = SELECT_ALL + " WHERE name = ?";
	private final String SELECT_LIST = SELECT_ALL + " WHERE id >= ? AND id <= ?";
	private final String COUNT = "SELECT COUNT(id) AS len FROM company";
	
	/**
	 * Renvoie les informations sur une compagnie à partir de l'identifiant
	 * @param id l'identifiant de la compagnie
	 * @return une compagnie
	 */
	public Optional<Company> find(long id) {
		Optional<Company> company = Optional.empty();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_ID)) {
			
			statement.setLong(1, id);
			
			ResultSet res = statement.executeQuery();
			
			company = CompanyMapper.resultSetToCompany(res);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return company;
	}

	/**
	 * Renvoie les informations sur une compagnie à partir du nom
	 * @param name le nom de la compagnie
	 * @return une compagnie
	 */
	public Optional<Company> find (String name) {
		Optional<Company> company = Optional.empty();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_NAME)) {
			
			statement.setString(1, name);
			
			ResultSet res = statement.executeQuery();
			
			company = CompanyMapper.resultSetToCompany(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return company;
	}

	public ArrayList<Company> listAll() {
		ArrayList<Company> companies = new ArrayList<Company>();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_ALL)) {

			ResultSet res = statement.executeQuery();
			
			companies = CompanyMapper.resultSetToListCompany(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return companies;
	}

	public ArrayList<Company> list(Long idDebut, Long idFin) {
		ArrayList<Company> companies = new ArrayList<Company>();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_LIST)) {
			
			statement.setLong(1, idDebut);
			statement.setLong(2, idFin);
			
			ResultSet res = statement.executeQuery();
			
			companies = CompanyMapper.resultSetToListCompany(res);
			
		} catch (SQLException e) {
			logger.debug("Exception SQL", e);
			e.printStackTrace();
		}
		return companies;
	}

	public Long length() {
		Long len = 0L;
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(COUNT)) {

			ResultSet res = statement.executeQuery();

			res.next();
			len = res.getLong("len");

		} catch (SQLException e) {
			logger.debug("Exception SQL", e);
			e.printStackTrace();
		}
		return len;
	}

}
