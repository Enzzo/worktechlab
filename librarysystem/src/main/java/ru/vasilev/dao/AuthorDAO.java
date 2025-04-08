package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import ru.vasilev.entity.Author;

public interface AuthorDAO {
	Optional<Author> findById(Long id);
	Optional<Author> findByName(String name);
	List<Author> findAll();
	Author save(Author author);
	void deleteById(Long id);
	void delete(Author author);
}