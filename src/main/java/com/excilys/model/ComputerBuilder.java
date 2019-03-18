package com.excilys.model;

import java.sql.Timestamp;

public class ComputerBuilder {
	
	Long id;
	String name;
	Timestamp introduced;
	Timestamp discontinued;
	Company company;
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param id l'identifiant de l'ordinateur
	 * @return le ComputerBuilder
	 */
	ComputerBuilder withId (Long id) {
		this.id = id;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param name le nom de l'ordinateur
	 * @return le ComputerBuilder
	 */
	ComputerBuilder withName (String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param introduced
	 * @return le ComputerBuilder
	 */
	ComputerBuilder withIntroduced (Timestamp introduced) {
		this.introduced = introduced;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param discontinued
	 * @return le ComputerBuilder
	 */
	ComputerBuilder withDiscontinued (Timestamp discontinued) {
		this.discontinued = discontinued;
		return this;
	}
	
	/**
	 * Renvoie un ComputerBuilder
	 * @param company
	 * @return le ComputerBuilder
	 */
	ComputerBuilder withCompany (Company company) {
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
	ComputerBuilder withAllArguments (Long id, String name, Timestamp introduced, Timestamp discontinued, Company company) {
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
	 */
	Computer build () {
		return new Computer(id, name, introduced, discontinued, company);
	}
	
}
