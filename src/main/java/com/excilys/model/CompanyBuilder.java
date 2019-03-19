package com.excilys.model;

public class CompanyBuilder {
	
	private Long id;
	private String name;
	
	
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
		return new Company(id, name);
	}
	
}
