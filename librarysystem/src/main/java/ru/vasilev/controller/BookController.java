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
import ru.vasilev.dao.AuthorDAO;
import ru.vasilev.dao.BookDAO;
import ru.vasilev.entity.Author;
import ru.vasilev.entity.Book;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "Операции с книгами")
public class BookController {
	private final BookDAO bookDAO;
	private final AuthorDAO authorDAO;

	@Autowired
	public BookController(BookDAO bookDAO, AuthorDAO authorDAO) {
		this.bookDAO = bookDAO;
		this.authorDAO = authorDAO;
	}
	
	@GetMapping
	@Operation(summary = "Получить все книги", description = "Возвращает список всех книг")
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
	}

	@PostMapping
	@Operation(summary = "Создать книгу", description = "Создаёт книгу для указанного автора")
    public Book createBook(
    		@Parameter(description = "Название книги", required = true) @RequestParam String title,
            @Parameter(description = "ID автора", required = true) @RequestParam Long authorId) {
        Author author = authorDAO.findById(authorId)
            .orElseThrow(() -> new RuntimeException("Author not found"));
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return bookDAO.save(book);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по ID", description = "Возвращает данные книги по идентификатору")
    public Book getBook( @Parameter(description = "Идентификатор книги", required = true) @PathVariable Long id) {
        return bookDAO.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}