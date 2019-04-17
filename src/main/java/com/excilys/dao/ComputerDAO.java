package com.excilys.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;

@Repository
public class ComputerDAO {
	
	private final String SELECT_ALL = "SELECT ct.id, ct.name, ct.introduced, ct.discontinued, cn.id, cn.name "
			+ "FROM computer AS ct LEFT JOIN company AS cn ON ct.company_id = cn.id";
	private final String SELECT_ID = SELECT_ALL + " WHERE ct.id = ?";
	private final String SELECT_NAME = SELECT_ALL + " WHERE ct.name LIKE ? OR cn.name LIKE ?";
	private final String SELECT_LIST = SELECT_ALL + " WHERE ct.id >= ? AND ct.id <= ?";
	private final String INSERT = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM computer WHERE id = ?";
	private final String COUNT = "SELECT COUNT(id) AS len FROM computer";
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ComputerMapper computerMapper;
	
	public Optional<Computer> find(Long id) throws SQLException {
		Computer computer;
		try {
			JdbcTemplate select = new JdbcTemplate(dataSource);
			computer = select.queryForObject(SELECT_ID, new Object[] {id}, computerMapper);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		if (computer == null) {
			return Optional.empty();
		}
		return Optional.of(computer);
	}

	public ArrayList<Computer> find(String name) throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return (ArrayList<Computer>) select.query(SELECT_NAME, new Object[] {"%" + name + "%", "%" + name + "%"}, computerMapper);
	}

	public Optional<Computer> create(Computer comp) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(INSERT, new Object[] {comp.getName(), comp.getIntroduced(), comp.getDiscontinued(), comp.getCompany().getId()} );
		return Optional.of(comp);
	}

	public Optional<Computer> update(Computer comp) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(UPDATE, new Object[] {comp.getName(), comp.getIntroduced(), comp.getDiscontinued(), comp.getCompany().getId(), comp.getId()} );
		return Optional.of(comp);
	}

	public void delete(Computer comp) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(DELETE, new Object[] {comp.getId()} );
	}

	public List<Computer> listAll() throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SELECT_ALL, computerMapper);
	}

	public List<Computer> list(Long idDebut, Long idFin) throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SELECT_LIST, new Object[] {idDebut, idFin}, computerMapper);
	}

	public Long length() throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		return update.queryForObject(COUNT, Long.class);
	}

	public ArrayList<Computer> sort(String sortBy) throws SQLException {
		String ORDER_BY;
		if (sortBy == "name") {
			ORDER_BY = SELECT_ALL + " ORDER BY ct." + sortBy;
		} else {
			ORDER_BY = SELECT_ALL + " ORDER BY ct." + sortBy + " IS NULL, ct." + sortBy;
		}
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return (ArrayList<Computer>) select.query(ORDER_BY, computerMapper);
	}

}
