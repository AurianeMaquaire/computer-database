package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.ConnectionDAO;

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

	public Optional<Computer> find(long id) {
		Optional<Computer> computer = Optional.empty();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_ID)) {

			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();

			computer = ComputerMapper.resultSetToComputer(res);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computer;
	}

	public ArrayList<Computer> find(String name) {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_NAME)) {

			statement.setString(1, "%" + name + "%");
			statement.setString(2, "%" + name + "%");

			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computers;
	}

	public Optional<Computer> create(Computer comp) {
		Optional<Computer> computer = Optional.empty();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(INSERT)) {

			statement.setString(1, comp.getName());
			statement.setTimestamp(2, comp.getIntroduced());
			statement.setTimestamp(3, comp.getDiscontinued());
			statement.setLong(4, comp.getCompany().getId());

			statement.executeUpdate();
			computer = Optional.of(comp);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computer;
	}

	public Optional<Computer> update(Computer comp) {
		Optional<Computer> computer = Optional.empty();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(UPDATE)) {

			statement.setString(1, comp.getName());
			statement.setTimestamp(2, comp.getIntroduced());
			statement.setTimestamp(3, comp.getDiscontinued());
			statement.setLong(4, comp.getCompany().getId());
			statement.setLong(5, comp.getId());

			statement.executeUpdate();
			computer = Optional.of(comp);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computer;
	}

	public void delete(Computer comp) {
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(DELETE)) {

			statement.setLong(1, comp.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
	}

	public ArrayList<Computer> listAll() {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_ALL)) {

			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computers;
	}

	public ArrayList<Computer> list(Long idDebut, Long idFin) {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(SELECT_LIST)) {

			statement.setLong(1, idDebut);
			statement.setLong(2, idFin);

			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computers;
	}

	public Long length() {
		Long len = 0L;
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(COUNT)) {

			ResultSet res = statement.executeQuery();

			res.next();
			len = res.getLong("len");

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return len;
	}

	public ArrayList<Computer> sort(String sortBy) {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		String ORDER_BY = SELECT_ALL + " ORDER BY ct." + sortBy;
		
		try (PreparedStatement statement = ConnectionDAO.getInstance().prepareStatement(ORDER_BY)) {

			ResultSet res = statement.executeQuery();

			computers = ComputerMapper.resultSetToListComputer(res);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computers;
	}

}
