package ru.vasilev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dao.AuthorDAO;
import ru.vasilev.dto.AuthorDTO;
import ru.vasilev.entity.Author;

@Slf4j
@Service
@Transactional
public class AuthorService {
	private final AuthorDAO authorDAO;

	@Autowired
	public AuthorService(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	public AuthorDTO save(AuthorDTO authorDTO) {
		log.info("Начало выполнения операции save в сервисном слое.");
		
		Author author = authorDAO.save(Author.fromAuthorDTO(authorDTO));		
		authorDTO = AuthorDTO.fromAuthor(author);
		
		log.debug("операция выполнена успешно");
		return authorDTO;

	}

	public AuthorDTO findById(Long id) {
		log.info("Начало выполнения операции findById в сервисном слое.");
		return authorDAO.findById(id)
				.map(author -> {
					return AuthorDTO.fromAuthor(author);
				})
				.orElseThrow(() -> {
					log.error("Ошибка при выполнении операции findById({})", id);
					return new NoSuchElementException("Author not found with id: " + id);
				});
	}
	
	public AuthorDTO findByName(String name) {
		log.info("Начало выполнения операции findByName в сервисном слое.");
		return authorDAO.findByName(name)
				.map(author -> {
					return AuthorDTO.fromAuthor(author);
				})
				.orElseThrow(() -> {
					log.error("Ошибка при выполнении операции findByName({})", name);
					return new NoSuchElementException("Author not found with name: " + name);
				});
	}

	public List<AuthorDTO> findAll(){
		log.info("Начало выполнения операции findAll в сервисном слое.");
		List<AuthorDTO> authorsList = new ArrayList<>();
		for(Author author : authorDAO.findAll()) {
			authorsList.add(AuthorDTO.fromAuthor(author));
		}
		log.debug("операция выполнена успешно");
		return authorsList;
	}

	public void delete(Long id) {
		log.info("Начало выполнения операции delete в сервисном слое.");
		authorDAO.deleteById(id);
	}
}