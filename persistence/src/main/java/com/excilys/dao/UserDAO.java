package com.excilys.dao;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.model.User;
import com.excilys.model.UserRole;

@Repository
@Transactional
public class UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	CriteriaBuilder criteriaBuilder;
	CriteriaQuery<User> criteriaQuery;
	CriteriaUpdate<User> criteriaUpdate;
	Root<User> root;
	
	private Session getSession(SessionFactory sessionFactory) {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}
	
	public Optional<User> find(String username) {
		try (Session session = getSession(sessionFactory);) {
			this.criteriaBuilder = session.getCriteriaBuilder();
			this.criteriaQuery = criteriaBuilder.createQuery(User.class);
			this.root = criteriaQuery.from(User.class);

			this.criteriaQuery.select(this.root).where(this.criteriaBuilder.equal(this.root.get("username"), username));
			Query<User> query = session.createQuery(this.criteriaQuery);

			return Optional.ofNullable(query.setMaxResults(1).uniqueResult());
		}
	}
	
	public void create(User user) {
		try (Session session = getSession(sessionFactory);) {
			Transaction transaction = session.beginTransaction();

			session.save(user);

			transaction.commit();
		}
	}

	public void createUserRole(User user) {
		try (Session session = getSession(sessionFactory);) {
			Transaction transaction = session.beginTransaction();

			String username = user.getUsername();
			Optional<User> userOpt = find(username);
			Long userId = null;
			if (userOpt.isPresent()) {
				userId = userOpt.get().getId();
			}
			
			UserRole userRole = new UserRole(userId, 2L, username);
			session.save(userRole);
			
			transaction.commit();
		}
	}
	
	
}
