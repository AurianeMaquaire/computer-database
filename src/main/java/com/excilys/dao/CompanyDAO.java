package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.persistence.ConnectionDAO;

public class CompanyDAO {

	private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	private final String SELECT_ALL = "SELECT id, name FROM company";
	private final String SELECT_ID = SELECT_ALL + " WHERE id = ?";
	private final String SELECT_NAME = SELECT_ALL + " WHERE name = ?";
	private final String SELECT_LIST = SELECT_ALL + " WHERE id >= ? AND id <= ?";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	private final String DELETE_COMPUTERS = "DELETE FROM computer WHERE company_id = ?";
	private final String COUNT = "SELECT COUNT(id) AS len FROM company";

	/**
	 * Renvoie les informations sur une compagnie à partir de l'identifiant
	 * @param id l'identifiant de la compagnie
	 * @return une compagnie
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public Optional<Company> find(long id) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		Optional<Company> company = Optional.empty();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_ID)) {
			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();

			company = CompanyMapper.resultSetToCompany(res);
			conn.commit();

		} catch (SQLException e) {
			logger.error("Exception SQL", e);
			e.printStackTrace();
			conn.rollback();
			throw new DAOException("Erreur lors de la recherche d'une compagnie par id");
		} 
		conn.setAutoCommit(true);
		return company;
	}

	/**
	 * Renvoie les informations sur une compagnie à partir du nom
	 * @param name le nom de la compagnie
	 * @return une compagnie
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public Optional<Company> find (String name) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		Optional<Company> company = Optional.empty();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_NAME)) {
			statement.setString(1, name);

			ResultSet res = statement.executeQuery();

			company = CompanyMapper.resultSetToCompany(res);
			conn.commit();

		} catch (SQLException e) {
			logger.error("Exception SQL", e);
			e.printStackTrace();
			conn.rollback();
			throw new DAOException("Erreur lors de la recherche d'une compagnie par nom");
		} 
		conn.setAutoCommit(true);
		return company;
	}

	public ArrayList<Company> listAll() throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		ArrayList<Company> companies = new ArrayList<Company>();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
			ResultSet res = statement.executeQuery();

			companies = CompanyMapper.resultSetToListCompany(res);
			conn.commit();

		} catch (SQLException e) {
			logger.error("Exception SQL", e);
			e.printStackTrace();
			conn.rollback();
			throw new DAOException("Erreur lors de la liste des compagnies");
		} 
		conn.setAutoCommit(true);
		return companies;
	}

	public ArrayList<Company> list(Long idDebut, Long idFin) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		ArrayList<Company> companies = new ArrayList<Company>();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_LIST)) {
			statement.setLong(1, idDebut);
			statement.setLong(2, idFin);

			ResultSet res = statement.executeQuery();

			companies = CompanyMapper.resultSetToListCompany(res);
			conn.commit();

		} catch (SQLException e) {
			logger.error("Exception SQL", e);
			e.printStackTrace();
			conn.rollback();
			throw new DAOException("Erreur lors de la liste des compagnies par pages");
		}
		conn.setAutoCommit(true);
		return companies;
	}
	
	public Long length() throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		Long len = 0L;
		
		try (PreparedStatement statement = conn.prepareStatement(COUNT)) {
			ResultSet res = statement.executeQuery();

			res.next();
			len = res.getLong("len");
			conn.commit();

		} catch (SQLException e) {
			logger.error("Exception SQL", e);
			e.printStackTrace();
			conn.rollback();
			throw new DAOException("Erreur lors du calcul de la taille d'une liste de compagnies");
		} 
		conn.setAutoCommit(true);
		return len;
	}

	public void delete(Company company) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		
		try (PreparedStatement statementCompany = conn.prepareStatement(DELETE_COMPANY);
				PreparedStatement statementComputers = conn.prepareStatement(DELETE_COMPUTERS)) {
			
			statementComputers.setLong(1, company.getId());
			statementComputers.executeUpdate();
			
			statementCompany.setLong(1, company.getId());
			statementCompany.executeUpdate();
			
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la suppression d'une compagnie");
		} 
		conn.setAutoCommit(true);
	}

}
