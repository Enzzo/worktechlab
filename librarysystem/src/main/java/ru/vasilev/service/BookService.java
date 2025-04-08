package ru.vasilev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.dao.AuthorDAO;
import ru.vasilev.dao.BookDAO;
import ru.vasilev.dto.BookDTO;
import ru.vasilev.entity.Author;
import ru.vasilev.entity.Book;

@Service
@Transactional
public class BookService {
	private final BookDAO bookDAO;
	private final AuthorDAO authorDAO;

	@Autowired
	public BookService(BookDAO bookDAO, AuthorDAO authorDAO) {
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
	}

	public BookDTO save(String title, Long authorId) {
		Author author = authorDAO.findById(authorId).orElse(null);
		return BookDTO.fromBook(bookDAO.save(new Book(title, author)));
	}

	public BookDTO findById(Long id) {
		return bookDAO.findById(id)
				.map(book -> {
					return BookDTO.fromBook(book);
				})
				.orElseThrow(() -> new NoSuchElementException("Book not found with id: "+ id));
	}

	public List<BookDTO> findAll(){
		List<BookDTO> bookList = new ArrayList<>();
		for(Book book : bookDAO.findAll()) {
			bookList.add(BookDTO.fromBook(book));
		}
		return bookList;
	}
	
	public void deleteById(Long id) {
		bookDAO.deleteById(id);
	}
	
	public void delete(BookDTO book) {
		bookDAO.delete(Book.fromBookDTO(book));
	}
}