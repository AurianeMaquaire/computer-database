package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.model.Company;

public class CompanyDAO extends DAO<Company> {

	@Override
	public Company find(long id) {
		Company company = null;
		try {
			Statement statement = connect.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT id, name FROM company "
					+ "WHERE id = '" + id + "'");
						
			if (res.next()) {
				company = new Company(res.getLong("id"), res.getString("name"));
			} else {
				System.out.println("This company doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		try {
			Statement statement = connect.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT id, name FROM company "
					+ "WHERE name = '" + name + "'");
						
			if (res.next()) {
				company = new Company(res.getLong("id"), res.getString("name"));
			} else {
				System.out.println("This company doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	public ArrayList<Company> list() {
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			Statement statement = connect.createStatement();
			ResultSet res;
			res = statement.executeQuery("SELECT company.id, company.name FROM company");
						
			while (res.next()) {
				Company comp = new Company(res.getLong("id"), res.getString("name"));
				companies.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}

}
