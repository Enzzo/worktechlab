package ru.vasilev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dto.BookDTO;
import ru.vasilev.entity.Author;
import ru.vasilev.entity.Book;
import ru.vasilev.repository.AuthorRepository;
import ru.vasilev.repository.BookRepository;

@Slf4j
@Service
@Transactional
public class BookService {
	private final BookRepository bookDAO;
	private final AuthorRepository authorDAO;

	@Autowired
	public BookService(BookRepository bookDAO, AuthorRepository authorDAO) {
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
	}

	public BookDTO save(String title, Long authorId) {
		log.info("Начало выполнения операции save в сервисном слое.");
		Author author = authorDAO.findById(authorId).orElse(null);
		return BookDTO.fromBook(bookDAO.save(new Book(title, author)));
	}

	public BookDTO findById(Long id) {
		log.info("Начало выполнения операции findById в сервисном слое.");
		return bookDAO.findById(id)
				.map(book -> {
					return BookDTO.fromBook(book);
				})
				.orElseThrow(() ->{
					log.error("Ошибка при выполнении операции findById({})", id);
					return new NoSuchElementException("Book not found with id: "+ id);
				});
	}

	public List<BookDTO> findAll(){
		log.info("Начало выполнения операции findAll в сервисном слое.");
		List<BookDTO> bookList = new ArrayList<>();
		for(Book book : bookDAO.findAll()) {
			bookList.add(BookDTO.fromBook(book));
		}
		log.debug("операция выполнена успешно");
		return bookList;
	}
	
	public void deleteById(Long id) {
		log.info("Начало выполнения операции deleteById в сервисном слое.");
		bookDAO.deleteById(id);
	}
	
	public void delete(BookDTO book) {
		log.info("Начало выполнения операции delete в сервисном слое.");
		bookDAO.delete(Book.fromBookDTO(book));
	}
}