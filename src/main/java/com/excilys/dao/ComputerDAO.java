package com.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerDAO extends DAO<Computer> {
	
	private static final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	private int nbComputers = 0;

	@Override
	public Computer find(long id) {
		Computer computer = null;
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT cn.id, cn.name, ct.id, "
					+ "ct.name, ct.introduced, ct.discontinued FROM computer AS ct "
					+ "LEFT JOIN company AS cn ON ct.id = cn.id WHERE ct.id = " + id);
						
			if (res.next()) {
				Company company = new Company(res.getLong("cn.id"), res.getString("cn.name"));
				Computer tmp = new Computer(res.getLong("ct.id"), res.getString("ct.name"),
						res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
						company);
				computer = tmp;
			} else {
				System.out.println("This computer doesn't exixst");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computer;
	}

	@Override
	public Computer find(String name) {
		return null;
	}
	
	@Override
	public Computer create(Computer obj) {
		Computer computer = null;
		try (PreparedStatement statement = connect.prepareStatement("INSERT INTO computer "
				+ "(name, introduced, discontinued, company_id)"
				+ "VALUES (?, ?, ?, ?)")) {
			
			statement.setString(1, obj.getName());
			statement.setTimestamp(2, obj.getIntroduced());
			statement.setTimestamp(3, obj.getDiscontinued());
			statement.setLong(4, obj.getCompany().getId());
			
			statement.executeUpdate();
			computer = obj;
			nbComputers = nbComputers + 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computer;
	}

	@Override
	public Computer update(Computer obj) {
		Computer computer = null;
		try (PreparedStatement statement = connect.prepareStatement("UPDATE computer "
				+ "SET name=? WHERE id = ?")) {
			
			statement.setString(1, obj.getName());
			statement.setLong(2, obj.getId());

			statement.executeUpdate();
			computer = obj;
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computer;
	}

	@Override
	public void delete(Computer obj) {
		try (PreparedStatement statement = connect.prepareStatement("DELETE FROM computer "
				+ "WHERE id = ?")) {
			
			statement.setLong(1, obj.getId());

			statement.executeUpdate();
			nbComputers = nbComputers -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
	}

	@Override
	public ArrayList<Computer> listAll() {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT cn.id, cn.name, ct.id, "
					+ "ct.name, ct.introduced, ct.discontinued FROM computer AS ct "
					+ "LEFT JOIN company AS cn ON ct.id = cn.id");
						
			while (res.next()) {
				Company company = new Company(res.getLong("cn.id"), res.getString("cn.name"));
				Computer comp = new Computer(res.getLong("ct.id"), res.getString("ct.name"),
						res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
						company);
				computers.add(comp);
				nbComputers = nbComputers + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computers;
	}

	@Override
	public ArrayList<Computer> list(Long idDebut, Long idFin) {
		//if (idFin > nbComputers - 20) return null;
		ArrayList<Computer> computers = new ArrayList<Computer>();
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT cn.id, cn.name, ct.id, "
					+ "ct.name, ct.introduced, ct.discontinued FROM computer AS ct "
					+ "LEFT JOIN company AS cn ON ct.id = cn.id "
					+ "WHERE ct.id>=" + idDebut + " AND ct.id<=" + idFin);
						
			while (res.next()) {
				Company company = new Company(res.getLong("cn.id"), res.getString("cn.name"));
				Computer comp = new Computer(res.getLong("ct.id"), res.getString("ct.name"),
						res.getTimestamp("introduced"), res.getTimestamp("discontinued"), 
						company);
				computers.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return computers;
	}

	@Override
	public Long length() {
		Long len = 0L;
		try (Statement statement = connect.createStatement()) {
			
			ResultSet res = statement.executeQuery("SELECT COUNT(*) AS len FROM computer");
						
			res.next();
			len = res.getLong("len");
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("Exception SQL", e);
		}
		return len;
	}

}
