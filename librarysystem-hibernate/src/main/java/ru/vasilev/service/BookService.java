package ru.vasilev.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.Author;
import ru.vasilev.model.Book;

@Service
@Transactional
public class BookService {
	private final SessionFactory sessionFactory;

	public BookService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Book create(String title, Long authorId) {
		Session session = sessionFactory.getCurrentSession();
		Author author = session.get(Author.class, authorId);
		Book book = new Book(title, author);
		session.persist(book);
		return book;
	}

	public Book getById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Book.class, id);
	}

	public List<Book> getAll(){
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Book", Book.class).list();
	}
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, id);
		if(book != null) {
			session.remove(book);
		}
	}
}