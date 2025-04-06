package ru.vasilev.service;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.Book;
import ru.vasilev.model.BorrowRecord;
import ru.vasilev.model.User;

@Service
@Transactional
public class BorrowRecordService {
	private final SessionFactory sessionFactory;

	public BorrowRecordService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public BorrowRecord borrowBook(User user, Book book) {
		Session session = sessionFactory.getCurrentSession();
		BorrowRecord br = new BorrowRecord(user, book);
		session.persist(br);
		return br;
	}
	
	public BorrowRecord returnBook(Long id) {
		Session session = sessionFactory.getCurrentSession();
		
		BorrowRecord br = session.get(BorrowRecord.class, id);
		if(br != null && br.getReturnDate() == null) {
			br.setReturnDate(LocalDateTime.now());
			session.persist(br);
		}
		
		return br;
	}
}