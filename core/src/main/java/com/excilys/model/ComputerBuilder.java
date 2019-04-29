package com.excilys.model;

import java.sql.Timestamp;

import com.excilys.exception.ModelException;

public class ComputerBuilder {
		
	private Long id = 0L;
	private String name = null;
	private Timestamp introduced = null;
	private Timestamp discontinued = null;
	private Company company = null;
	
	/**
	 * Construit un ordinateur vide
	 * @return l'ordinateur
	 */
	public ComputerBuilder empty() {
		this.id = 0L;
		this.name = null;
		this.introduced = null;
		this.discontinued = null;
		this.company = null;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param id l'identifiant de l'ordinateur
	 * @return le ComputerBuilder
	 */
	public ComputerBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param name le nom de l'ordinateur
	 * @return le ComputerBuilder
	 */
	public ComputerBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param introduced
	 * @return le ComputerBuilder
	 */
	public ComputerBuilder withIntroduced(Timestamp introduced) {
		this.introduced = introduced;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param discontinued
	 * @return le ComputerBuilder
	 */
	public ComputerBuilder withDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param company
	 * @return le ComputerBuilder
	 */
	public ComputerBuilder withCompany(Company company) {
		this.company = company;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param id l'identifiant de l'ordinateur
	 * @param name le nom de l'ordinateur
	 * @param introduced
	 * @param discontinued
	 * @param company
	 * @return le ComputerBuilder
	 */
	public ComputerBuilder withAllArguments(Long id, String name, Timestamp introduced, Timestamp discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
		return this;
	}
	
	/**
	 * Construit un ordinateur
	 * @return l'odinateur
	 * @throws ModelException 
	 */
	public Computer build() throws ModelException {
		Computer computer = new Computer();
		
		if (this.id == null) {
			throw new ModelException("L'ordinateur a un id null");
		}
		computer.setId(id);
		
		computer.setName(name);
		
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		
		computer.setCompany(company);
		
		return computer;
	}
	
}
