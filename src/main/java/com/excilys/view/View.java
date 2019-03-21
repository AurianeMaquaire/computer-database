package com.excilys.view;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.model.ChoixUtilisateur;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.ConnectionDAO;

public class View {
	
	static ComputerDAO computerDao = null;
	static CompanyDAO companyDao = null;
	static Scanner scanner;
	int nbChoisi;
	
	public View() {
		super();
		computerDao = new ComputerDAO();
		companyDao = new CompanyDAO();
		scanner = new Scanner(System.in);
		console();
	}
	
	
	/**
	 * Interagit avec l'utilisateur via la console
	 */
	public void console() {
		menu();
		nbChoisi = scanner.nextInt();
		ChoixUtilisateur choix = ChoixUtilisateur.values()[8];
		
		if (nbChoisi > choix.ordinal()) {
			System.out.println("Nombre non valide, veuillez en sélectionner un autre:");
			console();
			return;
		}
		
		choix = ChoixUtilisateur.values()[nbChoisi];
				
		switch(choix) {
		case AFFICHER_LISTE_ORDINATEURS: 
			afficherListeComputers();
			break;
			
		case AFFICHER_PAGE_ORDINATEURS:
			afficherPageComputers();
			break;
			
		case AFFICHER_LISTE_COMPAGNIES:
			afficherListeCompanies();
			break;
			
		case AFFICHER_PAGE_COMPAGNIES:
			afficherPageCompanies();
			break;
			
		case AFFICHER_DETAILS_ORDINATEUR:
			afficherDetailsOrdinateur();
			break;
			
		case CREER_ORDINATEUR:
			creerOrdinateur();
			break;
			
		case METTRE_A_JOUR_ORDINATEUR:
			mettreAJourOrdinateur();
			break;

		case SUPPRIMER_ORDINATEUR:
			supprimerOrdinateur();
			break;
			
		case QUITTER:
			System.out.println("Application fermée");
			scanner.close();
			ConnectionDAO.closeInstance();
			return;
			
		}
		console();
	}

	/**
	 * Affiche les choix possibles pour l'utilisateur
	 */
	private void menu() {
		System.out.println("");
		System.out.println("0 : Afficher la liste des ordinateurs");
		System.out.println("1 : Afficher la liste des ordinateurs par pages");
		System.out.println("2 : Afficher la liste des compagnies");
		System.out.println("3 : Afficher la liste des compagnies par pages");
		System.out.println("4 : Afficher des détails sur un ordinateur");
		System.out.println("5 : Créer un ordinateur");
		System.out.println("6 : Mettre un jour un ordinateur");
		System.out.println("7 : Supprimer un ordinateur");
		System.out.println("8 : Quitter");
	}


	/**
	 * Affiche la liste des ordinateurs
	 */
	public void afficherListeComputers() {
		ArrayList<Computer> computers = computerDao.listAll();
		System.out.println("Affichage de la liste des ordinateurs");
		for(Computer c : computers) {
			System.out.println(c);
		}
	}
	
	/**
	 * Affiche la liste des ordinateurs par pages
	 */
	public void afficherPageComputers() {
		System.out.println("Id de début:");
		Long idDebut = scanner.nextLong();
		System.out.println("Nombre d'ordinateurs à afficher:");
		Long interval = scanner.nextLong();
		
		Long idFin = idDebut + interval - 1;
		Long max = computerDao.length();
		if (idFin < max) {
			idFin = idDebut + interval - 1;
		} else if (idDebut > idFin) {
			idFin = idDebut;
		} else {
			idFin = max;
		}
		
		ArrayList<Computer> computers = computerDao.list(idDebut, idFin);
		System.out.println("Affichage de la liste des ordinateurs de " + idDebut + " à " + idFin);
		for(Computer c : computers) {
			System.out.println(c);
		}
	}

	/**
	 * Affiche la liste des compagnies
	 */
	public void afficherListeCompanies() {
		ArrayList<Company> companies = companyDao.listAll();
		System.out.println("Affichage de la liste des compagnies");
		for(Company c : companies) {
			System.out.println(c);
		}
	}
	
	/**
	 * Affiche la liste des compagnies par pages
	 */
	public void afficherPageCompanies() {
		System.out.println("Id de début:");
		Long idDebut = scanner.nextLong();
		System.out.println("Nombre de compagnies à afficher:");
		Long interval = scanner.nextLong();
		
		Long idFin = idDebut + interval - 1;
		Long max = companyDao.length();
		if (idFin < max) {
			idFin = idDebut + interval - 1;
		} else if (idDebut > idFin) {
			idFin = idDebut;
		} else {
			idFin = max;
		}
		
		ArrayList<Company> companies = companyDao.list(idDebut, idFin);
		System.out.println("Affichage de la liste des compagnies de " + idDebut + " à " + idFin);
		for(Company c : companies) {
			System.out.println(c);
		}
	}

	/**
	 * Affiche les détails d'un ordinateur
	 * @param id l'identifiant de l'ordinateur
	 */
	public void afficherDetailsOrdinateur() {
		System.out.println("Id de l'ordinateur:");
		Long idOrdi = scanner.nextLong();
		Optional<Computer> computer = computerDao.find(idOrdi);
		
		if (computer.isPresent()) {
			System.out.println(computer.get());
		} else {
			System.out.println("Cet ordinateur n'existe pas");
		}
	}

	/**
	 * Crée un ordinateur
	 * @param name le nom de l'ordinateur
	 * @param introduced la date de début
	 * @param discontinued la date de fin
	 * @param company la compagnie à laquelle il appartient
	 */
	public void creerOrdinateur() {
		System.out.println("Nom de l'ordinateur à créer:");
		String nomOrdi = scanner.next();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(new Date());
		Timestamp tsIntroduced = Timestamp.valueOf(currentTime);
		
		System.out.println("Nom de la compagnie:");
		String company_name = scanner.next();
		Optional<Company> company = companyDao.find(company_name);
		
		if(company.isPresent()) {
			Computer computer = new Computer(nomOrdi, tsIntroduced, null, company.get());
			computerDao.create(computer);
			System.out.println("L'ordinateur " + nomOrdi + " a bien été crée");
		} else {
			System.out.println("Cette compagnie n'existe pas");
		}
	}

	/**
	 * Met à jour un ordinateur
	 */
	public void mettreAJourOrdinateur() {
		System.out.println("Id de l'ordinateur à mettre à jour:");
		Long idOrdi = scanner.nextLong();
		
		Optional<Computer> computer = computerDao.find(idOrdi);

		if (computer.isPresent()) {
			System.out.println(computer.get());
			
			System.out.println("Nouveau nom de l'ordinateur:");
			String nomOrdi = scanner.next();
			
			computer.get().setName(nomOrdi);
			computerDao.update(computer.get());
			
			System.out.println("L'ordinateur " + idOrdi + " a bien été mit à jour");
			computer = computerDao.find(idOrdi);
			System.out.println(computer.get());
		} else {
			System.out.println("Cet ordinateur n'existe pas");
		}
	}

	/**
	 * Supprime un ordinateur
	 */
	public void supprimerOrdinateur() {
		System.out.println("Id de l'ordinateur à supprimer:");
		Long idOrdi = scanner.nextLong();
		
		Optional<Computer> computer = computerDao.find(idOrdi);
		if (computer.isPresent()) {
			computerDao.delete(computer.get());
			System.out.println("L'ordinateur " + idOrdi + " a bien été supprimé");
		} else {
			System.out.println("Cet ordinateur n'existe pas");
		}
	}
	
}
