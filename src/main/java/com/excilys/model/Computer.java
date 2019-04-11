package com.excilys.model;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLong;

public class Computer {
	
	AtomicLong incr = new AtomicLong(560);
	private Long id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private Company company;
	
	/**
	 * Constructeur sans argument
	 */
	public Computer() {
		super();
	}
	
	/**
	 * Constructeur avec cinq arguments
	 * @param id l'identifiant de l'ordinateur
	 * @param name le nom de l'ordinateur
	 * @param introduced la date à laquelle il a été mit sur le marché
	 * @param discontinued la date à laquelle il a été retiré du marché
	 * @param company la compagnie de l'ordinateur
	 */
	public Computer(Long id, String name, Timestamp introduced, Timestamp discontinued, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	/**
	 * Constructeur avec quatre arguments
	 * @param name le nom de l'ordinateur
	 * @param introduced la date à laquelle il a été mit sur le marché
	 * @param discontinued la date à laquelle il a été retiré du marché
	 * @param company la compagnie de l'ordinateur
	 */
	public Computer(String name, Timestamp introduced, Timestamp discontinued, Company company) {
		super();
		this.id = incr.getAndIncrement();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((incr == null) ? 0 : incr.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		
		Computer computer = (Computer) obj;
		
		if (this.id != computer.id) return false;
		
		if (this.name == null && computer.name != null) return false;
		if (this.name != null && computer.name == null) return false;
		if (this.name != null && computer.name != null && !this.name.equals(computer.name)) return false;
		
		if (this.introduced == null && computer.introduced != null) return false;
		if (this.introduced != null && computer.introduced == null) return false;
		if (this.introduced != null && computer.introduced != null && !this.introduced.equals(computer.introduced)) return false;
		
		if (this.discontinued == null && computer.discontinued != null) return false;
		if (this.discontinued != null && computer.discontinued == null) return false;
		if (this.discontinued != null && computer.discontinued != null && !this.discontinued.equals(computer.discontinued)) return false;
		
		if (this.company == null && computer.company != null) return false;
		if (this.company != null && computer.company == null) return false;
		if (this.company != null && computer.company != null && !this.company.equals(computer.company)) return false;
		
		return true;
	}
	
	/**
	 * @return l'identifiant de l'ordinateur
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @return le nom de l'ordinateur
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return la date à laquelle il a été mit sur le marché
	 */
	public Timestamp getIntroduced() {
		return this.introduced;
	}
	
	/**
	 * @return la date à laquelle il a été retiré du marché
	 */
	public Timestamp getDiscontinued() {
		return this.discontinued;
	}
	
	/**
	 * @return la compagnie de l'ordinateur
	 */
	public Company getCompany() {
		return this.company;
	}
	
	/**
	 * @param pId l'identifiant de l'ordinateur
	 */
	public void setId(Long pId) {
		this.id = pId;
	}
	
	/**
	 * @param pName le nom de l'ordinateur
	 */
	public void setName(String pName) {
		this.name = pName;
	}
	
	/**
	 * @param pIntroduced la date à laquelle il a été mit sur le marché
	 */
	public void setIntroduced(Timestamp pIntroduced) {
		this.introduced = pIntroduced;
	}
	
	/**
	 * @param pDiscontinued la date à laquelle il a été retiré du marché
	 */
	public void setDiscontinued(Timestamp pDiscontinued) {
		this.discontinued = pDiscontinued;
	}
	
	/**
	 * @param pCompany la compagnie de l'ordinateur
	 */
	public void setCompany(Company pCompany) {
		this.company = pCompany;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}
	
}
