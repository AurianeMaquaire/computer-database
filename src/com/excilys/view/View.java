package com.excilys.view;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.excilys.model.*;

public class View {
	
	static ConnectionDB conn = null;
	
	public View() {
		super();
		try {
			View.conn = new ConnectionDB();
			console();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Interagit avec l'utilisateur via la console
	 */
	public void console() {
		
		if (conn == null) {
			return;
		}
		
		System.out.println("1 : afficher la liste des ordinateurs");
		System.out.println("2 : afficher la liste des compagnies");
		System.out.println("3 : afficher des détails sur un ordinateur");
		System.out.println("4 : créer un ordinateur");
		System.out.println("5 : mettre un jour un ordinateur");
		System.out.println("6 : supprimer un ordinateur");
		
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
			Company company = conn.showCompany(company_name);
			
			creerOrdinateur(nomOrdi, tsIntroduced, null, company);
			console();
			break;
			
		case 5:
			System.out.println("Id de l'ordinateur à mettre à jour:");
			idOrdi = scanner.nextLong();
			
			Computer computer = conn.showComputer(idOrdi);
			System.out.println(computer);
			
			System.out.println("Nouveau nom de l'ordinateur:");
			nomOrdi = scanner.next();
			
			mettreAJourOrdinateur(idOrdi, nomOrdi, computer.getDiscontinued());
			computer = conn.showComputer(idOrdi);
			System.out.println(computer);
			
			console();
			break;
			
		case 6:
			System.out.println("Id de l'ordinateur à supprimer:");
			idOrdi = scanner.nextLong();
			
			supprimerOrdinateur(idOrdi);
			console();
			break;
			
		default:
			System.out.println("Erreur, nombre non valide");
		}
		
		scanner.close();
	}

	/**
	 * Affiche la liste des ordinateurs
	 */
	public static void afficherListeComputers() {
		ArrayList<Computer> computers = conn.listComputers();
		System.out.println("Affichage de la liste des ordinateurs");
		for(Computer c : computers) {
			System.out.println(c);
		}
	}

	/**
	 * Affiche la liste des compagnies
	 */
	public static void afficherListeCompanies() {
		ArrayList<Company> companies = conn.listCompanies();
		System.out.println("Affichage de la liste des companies");
		for(Company c : companies) {
			System.out.println(c);
		}
	}

	/**
	 * Affiche les détails d'un ordinateur
	 * @param id l'identifiant de l'ordinateur
	 */
	public static void afficherDetailsOrdinateur(Long id) {
		Computer computer = conn.showComputer(id);
		System.out.println(computer);
	}

	/**
	 * Crée un ordinateur
	 * @param name le nom de l'ordinateur
	 * @param introduced la date à laquelle il a été vendu
	 * @param discontinued la date à laquelle il n'est plus vendu
	 * @param company la compagnie à laquelle il appartient
	 */
	public static void creerOrdinateur(String name, Timestamp introduced, Timestamp discontinued, Company company) {
		conn.createComputer(name, introduced, discontinued, company);
		System.out.println("L'ordinateur " + name + " a bien été crée");
	}

	/**
	 * Met à jour un ordinateur
	 * @param id l'identifiant de l'ordinateur
	 * @param name le nom à mettre à jour
	 * @param discontinued le timestamp à mettre à jour
	 */
	public static void mettreAJourOrdinateur(Long id, String name, Timestamp discontinued) {
		conn.updateComputer(id, name, discontinued);
		System.out.println("L'ordinateur " + id + " a bien été mit à jour");
	}

	/**
	 * Supprime un ordinateur
	 * @param id l'identifiant de l'ordinateur
	 */
	public static void supprimerOrdinateur(Long id) {
		conn.deleteComputer(id);
		System.out.println("L'ordinateur " + id + " a bien été supprimé");
	}
	
}
