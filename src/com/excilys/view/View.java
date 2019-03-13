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
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.ConnectionDAO;

public class View {
	
	static Connection conn = null;
	static DAO<Computer> computerDao = null;
	static DAO<Company> companyDao = null;
	
	public View() {
		super();
		conn = ConnectionDAO.getInstance();
		computerDao = new ComputerDAO();
		companyDao = new CompanyDAO();
		console();
	}
	
	
	/**
	 * Interagit avec l'utilisateur via la console
	 */
	public void console() {
		
		if (conn == null) {
			return;
		}
		
		
		System.out.println("");
		System.out.println("1 : afficher la liste des ordinateurs");
		System.out.println("2 : afficher la liste des compagnies");
		System.out.println("3 : afficher des détails sur un ordinateur");
		System.out.println("4 : créer un ordinateur");
		System.out.println("5 : mettre un jour un ordinateur");
		System.out.println("6 : supprimer un ordinateur");
		System.out.println("7 : afficher la liste des ordinateurs 20 par 20");
		System.out.println("8 : afficher la liste des compagnies 20 par 20");
		System.out.println("9 : quitter");
		
		int nbChoisi = 0;
		Scanner scanner = new Scanner(System.in);
		nbChoisi = scanner.nextInt();
		
		Long idOrdi;
		String nomOrdi;
		
		switch(nbChoisi) {
		case 1: 
			afficherListeComputers();
			console();
			break;
			
		case 2:
			afficherListeCompanies();
			console();
			break;
			
		case 3:
			System.out.println("Id de l'ordinateur:");
			idOrdi = scanner.nextLong();
			
			afficherDetailsOrdinateur(idOrdi);
			console();
			break;
			
		case 4:
			System.out.println("Nom de l'ordinateur à créer:");
			nomOrdi = scanner.next();
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = df.format(new Date());
			Timestamp tsIntroduced = Timestamp.valueOf(currentTime);
			
			System.out.println("Nom de la compagnie:");
			String company_name = scanner.next();
			Company company = companyDao.find(company_name);
			
			creerOrdinateur(nomOrdi, tsIntroduced, null, company);
			console();
			break;
			
		case 5:
			System.out.println("Id de l'ordinateur à mettre à jour:");
			idOrdi = scanner.nextLong();
			
			Computer computer = computerDao.find(idOrdi);
			System.out.println(computer);
			
			System.out.println("Nouveau nom de l'ordinateur:");
			nomOrdi = scanner.next();
			
			mettreAJourOrdinateur(idOrdi, nomOrdi, computer.getDiscontinued());
			computer = computerDao.find(idOrdi);
			System.out.println(computer);
			
			console();
			break;
			
		case 6:
			System.out.println("Id de l'ordinateur à supprimer:");
			idOrdi = scanner.nextLong();
			
			supprimerOrdinateur(idOrdi);
			console();
			break;
			
		case 7:
			System.out.println("Id de début:");
			idOrdi = scanner.nextLong();
			
			afficherListePagesComputers(idOrdi);
			console();
			break;

		case 8:
			System.out.println("Id de début:");
			Long idComp = scanner.nextLong();
			
			afficherListePagesCompanies(idComp);
			console();
			break;
			
		case 9:
			System.out.println("Application fermée");
			break;
			
		default:
			System.out.println("Erreur, nombre non valide");
			console();
		}
		
		scanner.close();
	}

	/**
	 * Affiche la liste des ordinateurs
	 */
	public static void afficherListeComputers() {
		ArrayList<Computer> computers = computerDao.listAll();
		System.out.println("Affichage de la liste des ordinateurs");
		for(Computer c : computers) {
			System.out.println(c);
		}
	}
	
	/**
	 * Affiche la liste des ordinateurs par pages
	 */
	public static void afficherListePagesComputers(Long idDebut) {
		Long idFin = idDebut + 19;
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
	public static void afficherListePagesCompanies(Long idDebut) {
		Long idFin = idDebut + 19;
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
	public static void afficherDetailsOrdinateur(Long id) {
		Computer computer = computerDao.find(id);
		System.out.println(computer);
	}

	/**
	 * Crée un ordinateur
	 * @param name le nom de l'ordinateur
	 * @param introduced la date de début
	 * @param discontinued la date de fin
	 * @param company la compagnie à laquelle il appartient
	 */
	public static void creerOrdinateur(String name, Timestamp introduced, Timestamp discontinued, Company company) {
		Computer computer = new Computer(name, introduced, discontinued, company);
		computerDao.create(computer);
		System.out.println("L'ordinateur " + name + " a bien été crée");
	}

	/**
	 * Met à jour un ordinateur
	 * @param id l'identifiant de l'ordinateur
	 * @param name le nom à mettre à jour
	 * @param discontinued le timestamp à mettre à jour
	 */
	public static void mettreAJourOrdinateur(Long id, String name, Timestamp discontinued) {
		Computer computer = computerDao.find(id);
		computer.setName(name);
		computer.setDiscontinued(discontinued);
		computerDao.update(computer);
		System.out.println("L'ordinateur " + id + " a bien été mit à jour");
	}

	/**
	 * Supprime un ordinateur
	 * @param id l'identifiant de l'ordinateur
	 */
	public static void supprimerOrdinateur(Long id) {
		Computer computer = computerDao.find(id);
		computerDao.delete(computer);
		System.out.println("L'ordinateur " + id + " a bien été supprimé");
	}
	
}
