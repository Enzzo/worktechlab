package ru.vasilev.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.Book;
import ru.vasilev.repository.AuthorRepository;
import ru.vasilev.repository.BookRepository;

@Service
@Transactional
public class BookService {
	private final BookRepository bookRepo;
	private final AuthorRepository authorRepo;
	
	@Autowired
	public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
		this.bookRepo = bookRepo;
		this.authorRepo = authorRepo;
	}

	public Book save(String title, Long authorId) {
		return authorRepo.findById(authorId)
				.map(author -> bookRepo.save(new Book(title, author)))
				.orElse(null);
	}

	public Book findById(Long id) {
		return bookRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Book not found with id: "+ id));
	}

	public List<Book> findAll(){
		return bookRepo.findAll();
	}
	
	public void deleteById(Long id) {
		bookRepo.deleteById(id);
	}
}