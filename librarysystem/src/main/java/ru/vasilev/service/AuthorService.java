package ru.vasilev.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.Author;
import ru.vasilev.repository.AuthorRepository;

@Service
@Transactional
public class AuthorService {
	private final AuthorRepository authorRepo;

	@Autowired
	public AuthorService(AuthorRepository authorRepo) {
		this.authorRepo = authorRepo;
	}

	public Author save(Author author) {
		return authorRepo.save(author);
	}

	public Author findById(Long id) {
		return authorRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
	}

	public List<Author> findAll(){
		return authorRepo.findAll();
	}

	public void deleteById(Long id) {
		authorRepo.deleteById(id);
	}
}