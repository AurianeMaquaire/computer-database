package com.excilys.dto;

public class ComputerDTO {

	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private long companyId;
	private String companyName;

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
	 * @param companyId la compagnie de l'ordinateur
	 * @param companyName le nom de la compagnie
	 */
	public ComputerDTO(long id, String name, String introduced, String discontinued, long companyId, String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
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
	 * @return le nom de la compagnie de l'ordinateur
	 */
	public String getCompanyName() {
		return this.companyName;
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
	 * @param pCompanyId la compagnie de l'ordinateur
	 */
	public void setCompanyId(long pCompanyId) {
		this.companyId = pCompanyId;
	}

	/**
	 * @param pCompanyName la compagnie de l'ordinateur
	 */
	public void setCompanyName(String pCompanyName) {
		this.companyName = pCompanyName;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + ", companyName=" + companyName + "]";
	}

}
