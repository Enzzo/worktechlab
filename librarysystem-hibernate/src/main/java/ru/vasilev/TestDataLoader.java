package ru.vasilev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.vasilev.model.Author;
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

    public TestDataLoader(AuthorService authorService, BookService bookService,
                          UserService userService, BorrowRecordService borrowRecordService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.userService = userService;
        this.borrowRecordService = borrowRecordService;
    }

    @Override
    public void run(String... args) throws Exception {
//        Author author = authorService.create("Лев Толстой");
//        Book book = bookService.create("Война и мир", author.getId());
//        User user = userService.create("ivanov");
//        BorrowRecord record = borrowRecordService.borrowBook(user, book);
//        System.out.println("Книга выдана, запись ID: " + record.getId());
//        
//        // Пример возврата книги:
//        BorrowRecord updatedRecord = borrowRecordService.returnBook(record.getId());
//        System.out.println("Книга возвращена, время возврата: " + updatedRecord.getReturnDate());
    }

	@Bean
		 public DataSource dataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setUrl("jdbc:h2:mem:testdb");
		  dataSource.setUsername("sa");
		  dataSource.setPassword("");
		  return dataSource;
		 }
}