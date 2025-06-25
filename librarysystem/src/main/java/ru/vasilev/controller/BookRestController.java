package ru.vasilev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dto.BookDTO;
import ru.vasilev.service.AuthorService;
import ru.vasilev.service.BookService;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "Операции с книгами")
public class BookRestController {
	private final BookService bookService;
	private final AuthorService authorService;

	@Autowired
	public BookRestController(BookService bookService, AuthorService authorService) {
		this.bookService = bookService;
		this.authorService = authorService;
	}

	@GetMapping
	@Operation(summary = "Получить все книги", description = "Возвращает список всех книг")
	public List<BookDTO> getAllBooks() {

		log.info("Получен GET-запрос на /api/v1/books");
		log.debug("Передача запроса на уровень сервиса");
		return bookService.findAll();
	}

	@PostMapping
	@Operation(summary = "Создать книгу", description = "Создаёт книгу для указанного автора")
	public BookDTO createBook(
			@Parameter(description = "Название книги", required = true) @RequestParam String title,
			@Parameter(description = "ID автора", required = true) @RequestParam Long authorId) {

		log.info("Получен POST-запрос на /api/v1/books с параметрами");
		log.debug("Передача запроса на уровень сервиса");
		return bookService.save(title, authorId);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Получить книгу по ID", description = "Возвращает данные книги по идентификатору")
	public BookDTO getBook(@Parameter(description = "Идентификатор книги", required = true) @PathVariable Long id) {

		log.info("Получен GET-запрос на /api/v1/books/{}", id);
		log.debug("Передача запроса на уровень сервиса");
		return bookService.findById(id);
	}
}