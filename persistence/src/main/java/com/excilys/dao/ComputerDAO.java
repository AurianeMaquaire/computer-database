package com.excilys.dao;

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

	CriteriaBuilder criteriaBuilder;
	CriteriaQuery<Computer> criteriaQuery;
	CriteriaUpdate<Computer> criteriaUpdate;
	CriteriaDelete<Computer> criteriaDelete;
	Root<Computer> root;

	private Session getSession(SessionFactory sessionFactory) {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public Optional<Computer> find(Long id) {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			this.root = criteriaQuery.from(Computer.class);

			this.criteriaQuery.select(this.root).where(this.criteriaBuilder.equal(this.root.get("id"), id));
			Query<Computer> query = session.createQuery(this.criteriaQuery);

			return Optional.ofNullable(query.getSingleResult());
		}
	}

	public List<Computer> find(String name) {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			this.root = criteriaQuery.from(Computer.class);

			this.criteriaQuery.select(this.root).where(this.criteriaBuilder.like(this.root.<String>get("name"), name + "%"));
			Query<Computer> query = session.createQuery(this.criteriaQuery);

			return query.getResultList();
		}
	}

	public void create(Computer comp) {
		try (Session session = getSession(sessionFactory);) {
			Transaction transaction = session.beginTransaction();

			session.save(comp);

			transaction.commit();
		}
	}

	public Optional<Computer> update(Computer comp) {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Computer.class);
			this.root = criteriaUpdate.from(Computer.class);
			Transaction transaction = session.beginTransaction();

			this.criteriaUpdate.set("name", comp.getName());
			this.criteriaUpdate.set("introduced", comp.getIntroduced());
			this.criteriaUpdate.set("discontinued", comp.getDiscontinued());
			if (comp.getCompany() != null) {
				this.criteriaUpdate.set("company", comp.getCompany().getId());
			}
			this.criteriaUpdate.where(this.criteriaBuilder.equal(this.root.get("id"), comp.getId()));
			session.createQuery(this.criteriaUpdate).executeUpdate();

			transaction.commit();
			return Optional.ofNullable(comp);
		}
	}

	public void delete(Computer comp) {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaDelete = criteriaBuilder.createCriteriaDelete(Computer.class);
			this.root = criteriaDelete.from(Computer.class);
			Transaction transaction = session.beginTransaction();

			this.criteriaDelete.where(this.criteriaBuilder.equal(this.root.get("id"), comp.getId()));
			session.createQuery(this.criteriaDelete).executeUpdate();

			transaction.commit();
		}
	}

	public List<Computer> listAll() {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			this.root = criteriaQuery.from(Computer.class);

			this.criteriaQuery.select(this.root);
			Query<Computer> query = session.createQuery(this.criteriaQuery);

			return query.getResultList();
		}
	}

	public List<Computer> list(Long idDebut, Long idFin) {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			this.root = criteriaQuery.from(Computer.class);

			this.criteriaQuery.select(this.root).where(this.criteriaBuilder.between(this.root.get("id"), idDebut, idFin));
			Query<Computer> query = session.createQuery(this.criteriaQuery);

			return query.getResultList();
		}
	}

	public Long length() {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			this.root = criteriaQuery.from(Computer.class);

			CriteriaQuery<Long> criteriaQuery = this.criteriaBuilder.createQuery(Long.class);
			criteriaQuery.select(this.criteriaBuilder.count(this.root));
			Query<Long> query = session.createQuery(criteriaQuery);

			return query.getSingleResult();
		}
	}

	public List<Computer> sort(String sortBy) {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			this.root = criteriaQuery.from(Computer.class);

			this.criteriaQuery.orderBy(this.criteriaBuilder.asc(this.root.get(sortBy)));
			Query<Computer> query = session.createQuery(this.criteriaQuery);

			return query.getResultList();
		}
	}

}
