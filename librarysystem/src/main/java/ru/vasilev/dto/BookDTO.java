package ru.vasilev.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vasilev.entity.Author;
import ru.vasilev.entity.Book;
import ru.vasilev.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private Long id;
	private String title;
	private Author author;
	private List<User> userList = new ArrayList<>();
	
	public BookDTO(String title, Author author) {
		this.title = title;
		this.author = author;
	}
	
	public static BookDTO fromBook(Book book) {
		return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getUserList());
	}
}
