package com.excilys.model;

public class CompanyBuilder {
	
	Long id;
	String name;
	
	/**
	 * Renvoie un CompanyBuilder
	 * @param id l'identifiant de l'ordinateur
	 * @return le companyBuilder
	 */
	CompanyBuilder withId (Long id) {
		this.id = id;
		return this;
	}
	
	/**
	 * Renvoie un CompanyBuilder
	 * @param name le nom de l'ordinateur
	 * @return le companyBuilder
	 */
	CompanyBuilder withName (String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Renvoie un CompanyBuilder
	 * @param id l'identifiant de l'ordinateur
	 * @param name le nom de l'ordinateur
	 * @return le companyBuilder
	 */
	CompanyBuilder withIdAndName (Long id, String name) {
		this.id = id;
		this.name = name;
		return this;
	}
	
	/**
	 * Construit une compagnie 
	 * @return la compagnie
	 */
	Company build () {
		return new Company(id, name);
	}
	
	// Company c = new CompanyBuilder().withIdAndName(id, name).build();
	
}
