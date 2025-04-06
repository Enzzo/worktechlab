package ru.vasilev.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.User;

@Service
@Transactional
public class UserService {
	private final SessionFactory sessionFactory;

	public UserService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public User create(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User(username, "");
		session.persist(user);
		return user;
	}
	
	public User findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}
	
	public User findByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from User where username = :username", User.class)
				.setParameter("username", username).uniqueResult();
	}
	
	public List<User> getAll(){
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from User", User.class).list();
	}
	
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		if(user != null) {
			session.remove(user);
		}
	}
}