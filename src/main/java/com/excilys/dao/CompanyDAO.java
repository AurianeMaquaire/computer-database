package com.excilys.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Repository
@Transactional
public class CompanyDAO {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession(SessionFactory sessionFactory) {
		try {
		    return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    return sessionFactory.openSession();
		}
	}
	
	public Optional<Company> find(Long id) {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
			Root<Company> root = criteriaQuery.from(Company.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
			Query<Company> query = session.createQuery(criteriaQuery);
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		} catch (HibernateException e) {
			return Optional.empty();
		}
	}

	public Optional<Company> find(String name) {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
			Root<Company> root = criteriaQuery.from(Company.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.<String>get("name"), name));
			Query<Company> query = session.createQuery(criteriaQuery);
			return Optional.ofNullable(query.getSingleResult());
		} catch (HibernateException e) {
			return Optional.empty();
		}
	}

	public List<Company> listAll() {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
			Root<Company> root = criteriaQuery.from(Company.class);
			criteriaQuery.select(root);
			Query<Company> query = session.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (HibernateException e) {
			return new ArrayList<Company>();
		}
	}

	public List<Company> list(Long idDebut, Long idFin) {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
			Root<Company> root = criteriaQuery.from(Company.class);
			criteriaQuery.select(root).where(criteriaBuilder.between(root.get("id"), idDebut, idFin));
			Query<Company> query = session.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (HibernateException e) {
			return new ArrayList<Company>();
		}
	}

	public Long length() {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<Company> root = criteriaQuery.from(Company.class);
			criteriaQuery.select(criteriaBuilder.count(root));
			Query<Long> query = session.createQuery(criteriaQuery);
			return query.getSingleResult();
		}
	}

	public void delete(Company company) {
		try (Session session = getSession(sessionFactory)) {
			Transaction transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			
			CriteriaDelete<Computer> criteriaDeleteComputer = criteriaBuilder.createCriteriaDelete(Computer.class);
			Root<Computer> rootComputer = criteriaDeleteComputer.from(Computer.class);
			criteriaDeleteComputer.where(criteriaBuilder.equal(rootComputer.get("company"), company));
			session.createQuery(criteriaDeleteComputer).executeUpdate();
			
			CriteriaDelete<Company> criteriaDelete = criteriaBuilder.createCriteriaDelete(Company.class);
			Root<Company> root = criteriaDelete.from(Company.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("id"), company.getId()));
			session.createQuery(criteriaDelete).executeUpdate();
			
			transaction.commit();
		} 
	}

}
