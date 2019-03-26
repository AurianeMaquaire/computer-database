package com.excilys.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyBuilder {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyBuilder.class);
	
	private Long id = 0L;
	private String name = null;
	
	/**
	 * Constructeur sans argument
	 */
	public CompanyBuilder() {
		super();
	}
	
	/**
	 * Construit une compagnie vide
	 * @return la compagnie
	 */
	public CompanyBuilder empty () {
		this.id = 0L;
		this.name = null;
		return this;
	}
	
	/**
	 * Renvoie un CompanyBuilder
	 * @param id l'identifiant de l'ordinateur
	 * @return le CompanyBuilder
	 */
	public CompanyBuilder withId (Long id) {
		this.id = id;
		return this;
	}
	
	/**
	 * Renvoie un CompanyBuilder
	 * @param name le nom de l'ordinateur
	 * @return le CompanyBuilder
	 */
	public CompanyBuilder withName (String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Renvoie un CompanyBuilder
	 * @param id l'identifiant de l'ordinateur
	 * @param name le nom de l'ordinateur
	 * @return le CompanyBuilder
	 */
	public CompanyBuilder withIdAndName (Long id, String name) {
		this.id = id;
		this.name = name;
		return this;
	}
	
	/**
	 * Construit une compagnie 
	 * @return la compagnie
	 */
	public Company build () {
		Company company = new Company();
		if (this.id == null) {
			logger.warn("Id null");
			return null;
		} 
		company.setId(this.id);
		company.setName(this.name);
		return company;
	}
	
}
