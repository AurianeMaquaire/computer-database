package com.excilys.model;

public class Company {
	
	private Long id;
	private String name;
	
	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	// Getters
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	
	// Setters
	public void setId(Long pId) {
		this.id = pId;
	}
	public void setName(String pName) {
		this.name = pName;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	
}
