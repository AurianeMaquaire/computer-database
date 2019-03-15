package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;

public class CompanyDAO extends DAO<Company> {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	int nbCompanies = 0;

	@Override
	public Company find(long id) {
		Company company = null;
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT id, name FROM company "
					+ "WHERE id = '" + id + "'");
						
			if (res.next()) {
				company = new Company(res.getLong("id"), res.getString("name"));
			} else {
				System.out.println("This company doesn't exixst");
			}
			
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
	public Company find (String name) {
		Company company = null;
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT id, name FROM company "
					+ "WHERE name = '" + name + "'");
						
			if (res.next()) {
				company = new Company(res.getLong("id"), res.getString("name"));
			} else {
				System.out.println("This company doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return company;
	}

	@Override
	public Company create(Company obj) {
		return null;
	}

	@Override
	public Company update(Company obj) {
		return null;
	}

	@Override
	public void delete(Company obj) {
		return;
	}

	@Override
	public ArrayList<Company> listAll() {
		ArrayList<Company> companies = new ArrayList<Company>();
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT company.id, company.name "
					+ "FROM company");
						
			while (res.next()) {
				Company comp = new Company(res.getLong("id"), res.getString("name"));
				companies.add(comp);
				nbCompanies = nbCompanies + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return companies;
	}

	@Override
	public ArrayList<Company> list(Long idDebut, Long idFin) {
		//if (idFin > nbCompanies - 20) return null;
		ArrayList<Company> companies = new ArrayList<Company>();
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT company.id, company.name "
					+ "FROM company WHERE id>=" + idDebut + " AND id<=" + idFin);
						
			while (res.next()) {
				Company comp = new Company(res.getLong("id"), res.getString("name"));
				companies.add(comp);
			}
		} catch (SQLException e) {
			logger.debug("Exception SQL", e);
			e.printStackTrace();
		}
		return companies;
	}

	@Override
	public Long length() {
		Long len = 0L;
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT COUNT(*) AS len FROM company");
						
			res.next();
			len = res.getLong("len");
			
		} catch (SQLException e) {
			logger.debug("Exception SQL", e);
			e.printStackTrace();
		}
		return len;
	}

}
