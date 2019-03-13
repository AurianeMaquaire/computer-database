package com.excilys.dao;

import java.sql.Connection;
import java.util.ArrayList;

import com.excilys.persistence.ConnectionDAO;

public abstract class DAO<T> {
	
	public Connection connect = ConnectionDAO.getInstance();
	
	/**
	 * Récupère un objet via son identifiant
	 * @param id
	 * @return l'objet
	 */
	public abstract T find (long id);
	
	/**
	 * Récupère un objet via son nom
	 * @param name
	 * @return l'objet
	 */
	public abstract T find (String name);
	
	/**
	 * Crée une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */
	public abstract T create (T obj);
	
	/**
	 * Met à jour les données d'une entrée dans la base 
	 * @param obj
	 */
	public abstract T update (T obj);
	
	/**
	 * Supprime une entrée de la base
	 * @param obj
	 */
	public abstract void delete (T obj);
	
	/**
	 * Liste tous les objets
	 * @return
	 */
	public abstract ArrayList<T> listAll ();
	
	/**
	 * Liste les objets de idDebut à idFin
	 * @param idDebut
	 * @param idFin
	 * @return
	 */
	public abstract ArrayList<T> list (Long idDebut, Long idFin);

	
}
