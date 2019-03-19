package com.excilys.model;

public class Company {
	
	private Long id;
	private String name;
	
	/**
	 * Constructeur sans argument
	 */
	public Company() {
		super();
	}
	
	/**
	 * Constructeur avec deux arguments
	 * @param id l'identifiant de la compagnie
	 * @param name le nom de la compagnie
	 */
	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		
		Company company = (Company) obj;
		
		if (this.id != company.id) return false;
		
		if (this.name == null && company.name != null) return false;
		if (this.name != null && company.name == null) return false;
		if (this.name != null && company.name != null && !this.name.equals(company.name)) return false;
		
		return true;
	}
	
	/**
	 * @return l'identifiant de la compagnie
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @return le nom de la compagnie
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param pId l'identifiant de la compagnie
	 */
	public void setId(Long pId) {
		this.id = pId;
	}
	
	/**
	 * 
	 * @param pName le nom de la compagnie
	 */
	public void setName(String pName) {
		this.name = pName;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	
}
