package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import ru.vasilev.entity.Book;

public interface BookDAO {
	Optional<Book> findById(Long id);
	Optional<Book> findByTitle(String title);
	List<Book> findAll();
	Book save(Book book);
	void deleteById(Long id);
	void delete(Book book);
}
