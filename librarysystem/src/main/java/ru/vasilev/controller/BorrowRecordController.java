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
import ru.vasilev.model.Book;
import ru.vasilev.model.BorrowRecord;
import ru.vasilev.model.User;
import ru.vasilev.repository.BookRepository;
import ru.vasilev.repository.BorrowRecordRepository;
import ru.vasilev.repository.UserRepository;

@RestController
@RequestMapping("/api/borrow")
@Tag(name = "BorrowRecords", description = "Операции по выдаче и возврату книг")
public class BorrowRecordController {

	private final BorrowRecordRepository borrowRecordRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;

	public BorrowRecordController(BorrowRecordRepository borrowRecordRepository, UserRepository userRepository,
			BookRepository bookRepository) {
		this.borrowRecordRepository = borrowRecordRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}

	@GetMapping
	@Operation(summary = "Получить все записи выдачи книг", description = "Возвращает список всех записей выдачи")
	public List<BorrowRecord> getAllBorrowRecords() {
		return borrowRecordRepository.findAll();
	}

	@PostMapping("/borrow")
	@Operation(summary = "Выдать книгу", description = "Создаёт запись выдачи книги пользователю")
	public BorrowRecord borrowBook(@Parameter(description = "ID пользователя", required = true) @RequestParam Long userId,
            @Parameter(description = "ID книги", required = true) @RequestParam Long bookId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		BorrowRecord record = new BorrowRecord();
		record.setUser(user);
		record.setBook(book);
		record.setBorrowDate(LocalDateTime.now());
		// returnDate остаётся null до возврата

		return borrowRecordRepository.save(record);
	}

	@PostMapping("/return")
	@Operation(summary = "Вернуть книгу", description = "Обновляет запись выдачи, устанавливая дату возврата")
	public BorrowRecord returnBook(@Parameter(description = "ID записи выдачи", required = true) @RequestParam Long recordId) {
		BorrowRecord record = borrowRecordRepository.findById(recordId)
				.orElseThrow(() -> new RuntimeException("Record not found"));
		record.setReturnDate(LocalDateTime.now());
		return borrowRecordRepository.save(record);
	}
}