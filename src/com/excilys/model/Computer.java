package com.excilys.model;

import java.sql.Date;

public class Computer {
	
	private Long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Long company_id;
	
	// Getters
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public Date getIntroduced() {
		return this.introduced;
	}
	public Date getDiscontinued() {
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
	public void setIntroduced(Date pIntroduced) {
		this.introduced = pIntroduced;
	}
	public void setDiscontinued(Date pDiscontinued) {
		this.discontinued = pDiscontinued;
	}
	public void setId_company(Long pCompany_id) {
		this.company_id = pCompany_id;
	}
	
	
}
