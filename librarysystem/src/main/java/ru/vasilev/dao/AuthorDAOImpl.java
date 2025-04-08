package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.vasilev.entity.Author;

@Repository
public class AuthorDAOImpl implements AuthorDAO{

	private SessionFactory sessionFactory;
	
	@Autowired
	public AuthorDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Optional<Author> findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		Author author = session.get(Author.class, id);
		
		session.close();
		sessionFactory.close();
		
		return Optional.of(author);
	}

	@Override
	public Optional<Author> findByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Author where name = :name";
		Query<Author> query = session.createQuery(hql, Author.class);
		query.setParameter("name", name);
		
		Optional<Author> result = query.uniqueResultOptional();
		
		session.close();
		sessionFactory.close();
		return result;
	}
	
	@Override
	public List<Author> findAll() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Author> authorList = session.createQuery("from Author", Author.class).getResultList();
		
		session.close();
		sessionFactory.close();
		
		return authorList;
	}

	@Override
	public Author save(Author author) {
		Session session = sessionFactory.getCurrentSession();

		session.persist(author);
		
		session.close();
		sessionFactory.close();
		
		return author;
	}

	@Override
	public void delete(Author author) {
		Session session = sessionFactory.getCurrentSession();
		
		session.remove(author);
		
		session.close();
		sessionFactory.close();
	}	

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		Author author = session.get(Author.class, id);
		if(author != null) {
			session.remove(author);
		}
		
		session.close();
		sessionFactory.close();
	}
}
