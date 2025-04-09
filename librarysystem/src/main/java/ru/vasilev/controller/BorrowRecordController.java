package ru.vasilev.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dto.BookDTO;
import ru.vasilev.dto.BorrowRecordDTO;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.entity.BorrowRecord;
import ru.vasilev.service.BookService;
import ru.vasilev.service.BorrowRecordService;
import ru.vasilev.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/borrow")
@Tag(name = "BorrowRecords", description = "Операции по выдаче и возврату книг")
public class BorrowRecordController {

	private final BorrowRecordService borrowRecordService;
	private final UserService userService;
	private final BookService bookService;

	public BorrowRecordController(BorrowRecordService borrowRecordService, UserService userService,
			BookService bookService) {
		this.borrowRecordService = borrowRecordService;
		this.userService = userService;
		this.bookService = bookService;
	}

	@GetMapping
	@Operation(summary = "Получить все записи выдачи книг", description = "Возвращает список всех записей выдачи")
	public List<BorrowRecordDTO> getAllBorrowRecords() {
		log.info("Получен GET-запрос на /api/borrow");
		log.debug("Передача запроса на уровень сервиса");
		return borrowRecordService.findAll();
	}

	@PostMapping
	@Operation(summary = "Выдать книгу", description = "Создаёт запись выдачи книги пользователю")
	public BorrowRecordDTO borrowBook(
			@Parameter(description = "ID пользователя", required = true) @RequestParam Long userId,
			@Parameter(description = "ID книги", required = true) @RequestParam Long bookId) {
		
		log.info("Получен POST-запрос на /api/borrow с параметрами userId={} и bookId={} ", userId, bookId);
		
		UserDTO user = userService.findById(userId);
		BookDTO book = bookService.findById(bookId);
		
		log.debug("Передача запроса на уровень сервиса");
		return borrowRecordService.borrowBook(user, book);
	}

	@PostMapping("/return")
	@Operation(summary = "Вернуть книгу", description = "Обновляет запись выдачи, устанавливая дату возврата")
	public BorrowRecordDTO returnBook(
			@Parameter(description = "ID записи выдачи", required = true) @RequestParam Long recordId) {
		log.info("Получен POST-запрос на /api/borrow/return с параметром recordId={}", recordId);
		log.debug("Передача запроса на уровень сервиса");
		return borrowRecordService.returnBook(recordId);
	}
}