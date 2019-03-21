package com.excilys.dto;

public class CompanyDTO {
	
	private long id;
	private String name;
	
	/**
	 * Constructeur sans argument
	 */
	public CompanyDTO() {
		super();
	}
	
	/**
	 * Constructeur avec deux arguments
	 * @param id l'identifiant de la compagnie
	 * @param name le nom de la compagnie
	 */
	public CompanyDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		return "CompanyDTO [id=" + id + ", name=" + name + "]";
	}
	
	
}
