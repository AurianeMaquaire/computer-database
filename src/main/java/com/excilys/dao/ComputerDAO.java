package com.excilys.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;

@Repository
@Transactional
public class ComputerDAO {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	ComputerMapper computerMapper;

	@Autowired
	SessionFactory sessionFactory;

	private Session getSession(SessionFactory sessionFactory) {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public Optional<Computer> find(Long id) {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
			Query<Computer> query = session.createQuery(criteriaQuery);
			return Optional.ofNullable(query.getSingleResult());
		} 
	}

	public List<Computer> find(String name) {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(root).where(criteriaBuilder.like(root.<String>get("name"), name + "%"));
			Query<Computer> query = session.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (HibernateException e) {
			return new ArrayList<Computer>();
		}
	}

	public void create(Computer comp) {
		try (Session session = getSession(sessionFactory)) {
			Transaction transaction = session.beginTransaction();
			session.save(comp);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public Optional<Computer> update(Computer comp) {
		try (Session session = getSession(sessionFactory)) {
			Transaction transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<Computer> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Computer.class);
			Root<Computer> root = criteriaUpdate.from(Computer.class);
			criteriaUpdate.set("name", comp.getName());
			criteriaUpdate.set("introduced", comp.getIntroduced());
			criteriaUpdate.set("discontinued", comp.getDiscontinued());
			if (comp.getCompany() != null) {
				criteriaUpdate.set("company", comp.getCompany().getId());
			}
			
			criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), comp.getId()));
			session.createQuery(criteriaUpdate).executeUpdate();
			transaction.commit();
			return Optional.ofNullable(comp);
		} catch (HibernateException e) {
			return Optional.empty();
		}
	}

	public void delete(Computer comp) {
		try (Session session = getSession(sessionFactory)) {
			Transaction transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<Computer> criteriaDelete = criteriaBuilder.createCriteriaDelete(Computer.class);
			Root<Computer> root = criteriaDelete.from(Computer.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("id"), comp.getId()));
			session.createQuery(criteriaDelete).executeUpdate();
			transaction.commit();
		} catch (HibernateException e) {
		}
	}

	public List<Computer> listAll() {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(root);
			Query<Computer> query = session.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (HibernateException e) {
			return new ArrayList<Computer>();
		}
	}

	public List<Computer> list(Long idDebut, Long idFin) {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(root).where(criteriaBuilder.between(root.get("id"), idDebut, idFin));
			Query<Computer> query = session.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (HibernateException e) {
			return new ArrayList<Computer>();
		}
	}

	public Long length() {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(criteriaBuilder.count(root));
			Query<Long> query = session.createQuery(criteriaQuery);
			return query.getSingleResult();
		}
	}

	public List<Computer> sort(String sortBy) {
		try (Session session = getSession(sessionFactory)) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortBy)));
			Query<Computer> query = session.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (HibernateException e) {
			return new ArrayList<Computer>();
		}
	}

}
