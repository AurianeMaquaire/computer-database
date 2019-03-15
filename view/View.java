package com.excilys.view;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.DAO;
import com.excilys.model.ChoixUtilisateur;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.ConnectionDAO;

public class View {
	
	static Connection conn = null;
	static DAO<Computer> computerDao = null;
	static DAO<Company> companyDao = null;
	static Scanner scanner;
	
	public View() {
		super();
		conn = ConnectionDAO.getInstance();
		computerDao = new ComputerDAO();
		companyDao = new CompanyDAO();
		scanner = new Scanner(System.in);
		console();
	}
	
	
	/**
	 * Interagit avec l'utilisateur via la console
	 */
	public void console() {
		
		if (conn == null) {
			return;
		}
		
		menu();
		int nbChoisi = scanner.nextInt();
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
		System.out.println("0 : afficher la liste des ordinateurs");
		System.out.println("1 : afficher la liste des ordinateurs par pages");
		System.out.println("2 : afficher la liste des compagnies");
		System.out.println("3 : afficher la liste des compagnies par pages");
		System.out.println("4 : afficher des détails sur un ordinateur");
		System.out.println("5 : créer un ordinateur");
		System.out.println("6 : mettre un jour un ordinateur");
		System.out.println("7 : supprimer un ordinateur");
		System.out.println("8 : quitter");
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
	public static void afficherPageComputers() {
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
	public static void afficherListeCompanies() {
		ArrayList<Company> companies = companyDao.listAll();
		System.out.println("Affichage de la liste des companies");
		for(Company c : companies) {
			System.out.println(c);
		}
	}
	
	/**
	 * Affiche la liste des compagnies par pages
	 */
	public static void afficherPageCompanies() {
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
	public static void afficherDetailsOrdinateur() {
		System.out.println("Id de l'ordinateur:");
		Long idOrdi = scanner.nextLong();
		Computer computer = computerDao.find(idOrdi);
		System.out.println(computer);
	}

	/**
	 * Crée un ordinateur
	 * @param name le nom de l'ordinateur
	 * @param introduced la date de début
	 * @param discontinued la date de fin
	 * @param company la compagnie à laquelle il appartient
	 */
	public static void creerOrdinateur() {
		System.out.println("Nom de l'ordinateur à créer:");
		String nomOrdi = scanner.next();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(new Date());
		Timestamp tsIntroduced = Timestamp.valueOf(currentTime);
		
		System.out.println("Nom de la compagnie:");
		String company_name = scanner.next();
		Company company = companyDao.find(company_name);
		
		Computer computer = new Computer(nomOrdi, tsIntroduced, null, company);
		computerDao.create(computer);
		System.out.println("L'ordinateur " + nomOrdi + " a bien été crée");
	}

	/**
	 * Met à jour un ordinateur
	 */
	public static void mettreAJourOrdinateur() {
		System.out.println("Id de l'ordinateur à mettre à jour:");
		Long idOrdi = scanner.nextLong();
		
		Computer computer = computerDao.find(idOrdi);
		System.out.println(computer);
		
		System.out.println("Nouveau nom de l'ordinateur:");
		String nomOrdi = scanner.next();
		
		computer.setName(nomOrdi);
		computerDao.update(computer);
		
		System.out.println("L'ordinateur " + idOrdi + " a bien été mit à jour");
		computer = computerDao.find(idOrdi);
		System.out.println(computer);
	}

	/**
	 * Supprime un ordinateur
	 */
	public static void supprimerOrdinateur() {
		System.out.println("Id de l'ordinateur à supprimer:");
		Long idOrdi = scanner.nextLong();
		
		Computer computer = computerDao.find(idOrdi);
		computerDao.delete(computer);
		System.out.println("L'ordinateur " + idOrdi + " a bien été supprimé");
	}
	
}
