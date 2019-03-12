package com.excilys.model;

import java.sql.Timestamp;

public class Computer {
	
	private Long id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private Long company_id;
	
	public Computer(Long id, String name, Timestamp introduced, Timestamp discontinued, Long company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	
	// Getters
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public Timestamp getIntroduced() {
		return this.introduced;
	}
	public Timestamp getDiscontinued() {
		return this.discontinued;
	}
	public Long getCompany_id() {
		return this.company_id;
	}
	
	// Setters
	public void setId(Long pId) {
		this.id = pId;
	}
	public void setName(String pName) {
		this.name = pName;
	}
	public void setIntroduced(Timestamp pIntroduced) {
		this.introduced = pIntroduced;
	}
	public void setDiscontinued(Timestamp pDiscontinued) {
		this.discontinued = pDiscontinued;
	}
	public void setId_company(Long pCompany_id) {
		this.company_id = pCompany_id;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company_id=" + company_id + "]";
	}
	
	
}
