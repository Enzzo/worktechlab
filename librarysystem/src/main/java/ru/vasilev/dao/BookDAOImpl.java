package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ru.vasilev.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO{

	@Override
	public Optional<Book> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Book> findByTitle(String title) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub
		
	}
}
