package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ConnectionDAO;

@Repository
public class ComputerDAO {

	private static final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	private final String SELECT_ALL = "SELECT ct.id, ct.name, ct.introduced, ct.discontinued, cn.id, cn.name "
			+ "FROM computer AS ct LEFT JOIN company AS cn ON ct.company_id = cn.id";
	private final String SELECT_ID = SELECT_ALL + " WHERE ct.id = ?";
	private final String SELECT_NAME = SELECT_ALL + " WHERE ct.name LIKE ? OR cn.name LIKE ?";
	private final String SELECT_LIST = SELECT_ALL + " WHERE ct.id >= ? AND ct.id <= ?";
	private final String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM computer WHERE id = ?";
	private final String COUNT = "SELECT COUNT(id) AS len FROM computer";
	//private final String ORDER_BY = SELECT_ALL + " ORDER BY ? ASC";

	public Optional<Computer> find(long id) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		Optional<Computer> computer = Optional.empty();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_ID)) {
			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();

			computer = ComputerMapper.resultSetToComputer(res);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la recherche d'un ordinateur par identifiant");
		} 
		conn.setAutoCommit(true);
		return computer;
	}

	public ArrayList<Computer> find(String name) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		ArrayList<Computer> computers = new ArrayList<Computer>();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_NAME)) {
			statement.setString(1, "%" + name + "%");
			statement.setString(2, "%" + name + "%");

			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la recherche d'un ordinateur par nom");
		} 
		conn.setAutoCommit(true);
		return computers;
	}

	public Optional<Computer> create(Computer comp) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		Optional<Computer> computer = Optional.empty();
		
		try (PreparedStatement statement = conn.prepareStatement(INSERT)) {
			statement.setString(1, comp.getName());
			statement.setTimestamp(2, comp.getIntroduced());
			statement.setTimestamp(3, comp.getDiscontinued());
			statement.setLong(4, comp.getCompany().getId());

			statement.executeUpdate();
			computer = Optional.of(comp);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la création d'un ordinateur");
		} 
		conn.setAutoCommit(true);
		return computer;
	}

	public Optional<Computer> update(Computer comp) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		Optional<Computer> computer = Optional.empty();
		
		try (PreparedStatement statement = conn.prepareStatement(UPDATE)) {
			statement.setString(1, comp.getName());
			statement.setTimestamp(2, comp.getIntroduced());
			statement.setTimestamp(3, comp.getDiscontinued());
			statement.setLong(4, comp.getCompany().getId());
			statement.setLong(5, comp.getId());

			statement.executeUpdate();
			computer = Optional.of(comp);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la mise à jour d'un ordinateur");
		} 
		conn.setAutoCommit(true); 
		return computer;
	}

	public void delete(Computer comp) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		
		try (PreparedStatement statement = conn.prepareStatement(DELETE)) {
			statement.setLong(1, comp.getId());

			statement.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la suppression d'un ordinateur");
		} 
		conn.setAutoCommit(true);
	}

	public ArrayList<Computer> listAll() throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		ArrayList<Computer> computers = new ArrayList<Computer>();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la liste des ordinateurs");
		} 
		conn.setAutoCommit(true);
		return computers;
	}

	public ArrayList<Computer> list(Long idDebut, Long idFin) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		ArrayList<Computer> computers = new ArrayList<Computer>();
		
		try (PreparedStatement statement = conn.prepareStatement(SELECT_LIST)) {
			statement.setLong(1, idDebut);
			statement.setLong(2, idFin);

			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors de la liste des ordinateurs par pages");
		} 
		conn.setAutoCommit(true);
		return computers;
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
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors du calcul de la taille d'une liste d'ordinateurs");
		} 
		conn.setAutoCommit(true);
		return len;
	}

	public ArrayList<Computer> sort(String sortBy) throws SQLException, DAOException {
		Connection conn = ConnectionDAO.getInstance().getConnection();
		conn.setAutoCommit(false);
		ArrayList<Computer> computers = new ArrayList<Computer>();
		String ORDER_BY = SELECT_ALL + " ORDER BY ct." + sortBy;

		try (PreparedStatement statement = conn.prepareStatement(ORDER_BY)) {
			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Exception SQL", e);
			conn.rollback();
			throw new DAOException("Erreur lors du tri d'une liste d'ordinateurs");
		} 
		conn.setAutoCommit(true);
		return computers;
	}

}
