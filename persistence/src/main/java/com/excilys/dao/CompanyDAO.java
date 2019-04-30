package com.excilys.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.model.Company;
import com.excilys.model.Computer;

@Repository
@Transactional
public class CompanyDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	CriteriaBuilder criteriaBuilder;
	CriteriaQuery<Company> criteriaQuery;
	Root<Company> root;
	
	private Session getSession(SessionFactory sessionFactory) {
		try {
		    return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    return sessionFactory.openSession();
		}
	}
	
	public Optional<Company> find(Long id) {
		try (Session session = getSession(sessionFactory)) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = this.criteriaBuilder.createQuery(Company.class);
			this.root = this.criteriaQuery.from(Company.class);
			
			this.criteriaQuery.select(this.root).where(this.criteriaBuilder.equal(this.root.get("id"), id));
			Query<Company> query = session.createQuery(this.criteriaQuery);
			return Optional.ofNullable(query.getSingleResult());
			
		} catch (NoResultException e) {
			return Optional.empty();
		} catch (HibernateException e) {
			return Optional.empty();
		}
	}

	public Optional<Company> find(String name) {
		try (Session session = getSession(sessionFactory)) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = this.criteriaBuilder.createQuery(Company.class);
			this.root = this.criteriaQuery.from(Company.class);
			
			this.criteriaQuery.select(this.root).where(this.criteriaBuilder.equal(this.root.<String>get("name"), name));
			Query<Company> query = session.createQuery(this.criteriaQuery);
			return Optional.ofNullable(query.getSingleResult());
			
		} catch (HibernateException e) {
			return Optional.empty();
		}
	}

	public List<Company> listAll() {
		try (Session session = getSession(sessionFactory)) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = this.criteriaBuilder.createQuery(Company.class);
			this.root = this.criteriaQuery.from(Company.class);
			
			this.criteriaQuery.select(this.root);
			Query<Company> query = session.createQuery(this.criteriaQuery);
			return query.getResultList();
			
		} catch (HibernateException e) {
			return new ArrayList<Company>();
		}
	}

	public List<Company> list(Long idDebut, Long idFin) {
		try (Session session = getSession(sessionFactory)) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = this.criteriaBuilder.createQuery(Company.class);
			this.root = this.criteriaQuery.from(Company.class);
			
			this.criteriaQuery.select(this.root).where(this.criteriaBuilder.between(this.root.get("id"), idDebut, idFin));
			Query<Company> query = session.createQuery(this.criteriaQuery);
			return query.getResultList();
			
		} catch (HibernateException e) {
			return new ArrayList<Company>();
		}
	}

	public Long length() {
		try (Session session = getSession(sessionFactory)) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = this.criteriaBuilder.createQuery(Long.class);
			this.root = criteriaQuery.from(Company.class);
			
			criteriaQuery.select(this.criteriaBuilder.count(this.root));
			Query<Long> query = session.createQuery(criteriaQuery);
			return query.getSingleResult();
		}
	}

	public void delete(Company company) {
		try (Session session = getSession(sessionFactory)) {
			Transaction transaction = session.beginTransaction();
			this.criteriaBuilder = session.getCriteriaBuilder();
			
			CriteriaDelete<Computer> criteriaDeleteComputer = this.criteriaBuilder.createCriteriaDelete(Computer.class);
			Root<Computer> rootComputer = criteriaDeleteComputer.from(Computer.class);
			criteriaDeleteComputer.where(this.criteriaBuilder.equal(rootComputer.get("company"), company));
			session.createQuery(criteriaDeleteComputer).executeUpdate();
			
			CriteriaDelete<Company> criteriaDelete = this.criteriaBuilder.createCriteriaDelete(Company.class);
			Root<Company> root = criteriaDelete.from(Company.class);
			criteriaDelete.where(this.criteriaBuilder.equal(root.get("id"), company.getId()));
			session.createQuery(criteriaDelete).executeUpdate();
			
			transaction.commit();
		} 
	}

}
