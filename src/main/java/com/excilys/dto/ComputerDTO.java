package com.excilys.dto;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLong;

public class ComputerDTO {
	
	AtomicLong incr = new AtomicLong(560);
	private Long id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private CompanyDTO company;
	
	/**
	 * Constructeur sans argument
	 */
	public ComputerDTO() {
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
	public ComputerDTO(Long id, String name, Timestamp introduced, Timestamp discontinued, CompanyDTO company) {
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
	public ComputerDTO(String name, Timestamp introduced, Timestamp discontinued, CompanyDTO company) {
		super();
		this.id = incr.getAndIncrement();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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
	public CompanyDTO getCompany() {
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
	public void setCompany(CompanyDTO pCompany) {
		this.company = pCompany;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyDTO=" + company + "]";
	}
	
	
}
