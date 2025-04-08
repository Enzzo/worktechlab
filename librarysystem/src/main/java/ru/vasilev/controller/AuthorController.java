package ru.vasilev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.vasilev.dto.AuthorDTO;
import ru.vasilev.service.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
@Tag(name = "Authors", description = "Операции для работы с авторами")
public class AuthorController {
	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@PostMapping
	@Operation(summary = "Создать нового автора", description = "Создаёт нового автора с переданными данными")
	public AuthorDTO createAuthor(
			@Parameter(description = "Объект автора", required = true)
			@RequestBody AuthorDTO author) {
		return authorService.save(author);
	}
	
	@GetMapping
	@Operation(summary = "Получить всех авторов", description = "Возвращает список всех авторов")
	public List<AuthorDTO> getAll(){
		return authorService.findAll();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Получить автора по ID", description = "Возвращает данные автора по заданному идентификатору")
	public AuthorDTO getById(
			@Parameter(description = "Идентификатор автора", required = true)
			@PathVariable Long id) {
		return authorService.findById(id);
	}
	
	@GetMapping("/author")
	@Operation(summary = "Получить автора по имени", description = "Возвращает данные автора по заданному имени")
	public AuthorDTO getByName(
			@Parameter(description = "Параметр name - имя автора", required = true)
			@RequestParam String name) {
		return authorService.findByName(name);
	}
}