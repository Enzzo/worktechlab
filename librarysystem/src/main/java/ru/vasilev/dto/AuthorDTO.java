package ru.vasilev.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vasilev.entity.Author;
import ru.vasilev.entity.Book;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
	private Long id;
	private String name;
	private List<Book> bookList = new ArrayList<>();
	
	public static AuthorDTO fromAuthor(Author author) {
		return new AuthorDTO(author.getId(), author.getName(), author.getBookList());
	}
}