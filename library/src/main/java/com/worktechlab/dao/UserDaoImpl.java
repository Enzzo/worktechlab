package com.worktechlab.dao;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worktechlab.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private final SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(User user) {
		currentSession().persist(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		Query<User> query = currentSession().createQuery("from User where username is :username", User.class);
		query.setParameter("username", username);
		return query.uniqueResultOptional();
	}
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
}