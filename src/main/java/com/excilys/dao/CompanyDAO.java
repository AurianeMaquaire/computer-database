package com.excilys.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.exception.DAOException;
import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

@Repository
public class CompanyDAO {
	
	private final String SELECT_ALL = "SELECT id, name FROM company";
	private final String SELECT_ID = SELECT_ALL + " WHERE id = ?";
	private final String SELECT_NAME = SELECT_ALL + " WHERE name = ?";
	private final String SELECT_LIST = SELECT_ALL + " WHERE id >= ? AND id <= ?";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	private final String DELETE_COMPUTERS = "DELETE FROM computer WHERE company_id = ?";
	private final String COUNT = "SELECT COUNT(id) AS len FROM company";

	@Autowired
	DataSource dataSource;
	
	@Autowired
	CompanyMapper companyMapper;
	
	/**
	 * Renvoie les informations sur une compagnie à partir de l'identifiant
	 * @param id l'identifiant de la compagnie
	 * @return une compagnie
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public Optional<Company> find(Long id) throws SQLException {
		Company company;
		try {
			JdbcTemplate select = new JdbcTemplate(dataSource);
			company = select.queryForObject(SELECT_ID, new Object[] {id}, companyMapper);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		if (company == null) {
			return Optional.empty();
		}
		return Optional.of(company);
	}

	/**
	 * Renvoie les informations sur une compagnie à partir du nom
	 * @param name le nom de la compagnie
	 * @return une compagnie
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public Optional<Company> find(String name) throws SQLException, DAOException {
		Company company;
		try {
			JdbcTemplate select = new JdbcTemplate(dataSource);
			company = select.queryForObject(SELECT_NAME, new Object[] {name}, companyMapper);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		if (company == null) {
			return Optional.empty();
		}
		return Optional.of(company);
	}

	public List<Company> listAll() throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SELECT_ALL, companyMapper);
	}

	public List<Company> list(Long idDebut, Long idFin) throws SQLException {
		JdbcTemplate select = new JdbcTemplate(dataSource);
		return select.query(SELECT_LIST, new Object[] {idDebut, idFin}, companyMapper);
	}

	public Long length() throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		return update.queryForObject(COUNT, Long.class);
	}

	public void delete(Company company) throws SQLException {
		JdbcTemplate update = new JdbcTemplate(dataSource);
		update.update(DELETE_COMPUTERS, new Object[] {company.getId()} );
		update.update(DELETE_COMPANY, new Object[] {company.getId()} );
	}

}
