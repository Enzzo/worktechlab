package ru.vasilev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.vasilev.model.Author;
import ru.vasilev.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@PostMapping
	public Author createAuthor(@RequestBody Author author) {
		return authorService.save(author);
	}
	
	@GetMapping
	public List<Author> getAll(){
		return authorService.findAll();
	}
	
	@GetMapping("/{id}")
	public Author getById(@PathVariable Long id) {
		return authorService.findById(id);
	}
}