package ru.vasilev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.vasilev.dto.AuthorDTO;
import ru.vasilev.dto.BookDTO;
import ru.vasilev.entity.Author;
import ru.vasilev.service.AuthorService;
import ru.vasilev.service.BookService;
import ru.vasilev.service.BorrowRecordService;
import ru.vasilev.service.UserService;

@Component
public class TestDataLoader implements CommandLineRunner {

	private final AuthorService authorService;
    private final BookService bookService;
    private final UserService userService;
    private final BorrowRecordService borrowRecordService;

    @Autowired
    public TestDataLoader(AuthorService authorService, BookService bookService,
                          UserService userService, BorrowRecordService borrowRecordService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.userService = userService;
        this.borrowRecordService = borrowRecordService;
    }

    @Override
    public void run(String... args) throws Exception {
        AuthorDTO author = authorService.save(AuthorDTO.fromAuthor(new Author("Лев Толстой", null)));
        BookDTO book = bookService.save("Война и мир", author.getId());
    }
}