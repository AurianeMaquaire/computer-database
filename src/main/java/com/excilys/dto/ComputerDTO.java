package com.excilys.dto;

import java.util.concurrent.atomic.AtomicLong;

public class ComputerDTO {
	
	AtomicLong incr = new AtomicLong(560);
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private long companyId;
	
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
	public ComputerDTO(long id, String name, String introduced, String discontinued, long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	/**
	 * Constructeur avec quatre arguments
	 * @param name le nom de l'ordinateur
	 * @param introduced la date à laquelle il a été mit sur le marché
	 * @param discontinued la date à laquelle il a été retiré du marché
	 * @param company la compagnie de l'ordinateur
	 */
	public ComputerDTO(String name, String introduced, String discontinued, long companyId) {
		super();
		this.id = incr.getAndIncrement();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
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
	public String getIntroduced() {
		return this.introduced;
	}
	
	/**
	 * @return la date à laquelle il a été retiré du marché
	 */
	public String getDiscontinued() {
		return this.discontinued;
	}
	
	/**
	 * @return la compagnie de l'ordinateur
	 */
	public long getCompanyId() {
		return this.companyId;
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
	public void setIntroduced(String pIntroduced) {
		this.introduced = pIntroduced;
	}
	
	/**
	 * @param pDiscontinued la date à laquelle il a été retiré du marché
	 */
	public void setDiscontinued(String pDiscontinued) {
		this.discontinued = pDiscontinued;
	}
	
	/**
	 * @param pCompany la compagnie de l'ordinateur
	 */
	public void setCompany(long pCompanyId) {
		this.companyId = pCompanyId;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}
	
	
}
