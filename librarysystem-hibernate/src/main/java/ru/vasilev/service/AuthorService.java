package ru.vasilev.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.Author;

@Service
@Transactional
public class AuthorService {
	private final SessionFactory sessionFactory;

	public AuthorService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Author create(String name) {
		Session session = sessionFactory.getCurrentSession();
		Author author = new Author(name, null);
		session.persist(author);
		return author;
	}

	public Author getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Author.class, id);
	}

	public List<Author> getAll(){
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Author", Author.class).list();
	}

	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Author author = session.get(Author.class, id);
		if(author != null) {
			session.remove(author);
		}
	}
}