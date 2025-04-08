package ru.vasilev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.dao.AuthorDAO;
import ru.vasilev.dto.AuthorDTO;
import ru.vasilev.entity.Author;

@Service
@Transactional
public class AuthorService {
	private final AuthorDAO authorDAO;

	@Autowired
	public AuthorService(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	public AuthorDTO save(AuthorDTO author) {
		authorDAO.save(Author.fromAuthorDTO(author));
		return author;
	}

	public AuthorDTO findById(Long id) {
		return authorDAO.findById(id)
				.map(author -> {
					return AuthorDTO.fromAuthor(author);
				})
				.orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
	}
	
	public AuthorDTO findByName(String name) {
		return authorDAO.findByName(name)
				.map(author -> {
					return AuthorDTO.fromAuthor(author);
				})
				.orElseThrow(() -> new NoSuchElementException("Author not found with name: " + name));
	}

	public List<AuthorDTO> findAll(){
		List<AuthorDTO> authorsList = new ArrayList<>();
		for(Author author : authorDAO.findAll()) {
			authorsList.add(AuthorDTO.fromAuthor(author));
		}
		return authorsList;
	}

	public void deleteById(Long id) {
		authorDAO.deleteById(id);
	}
}