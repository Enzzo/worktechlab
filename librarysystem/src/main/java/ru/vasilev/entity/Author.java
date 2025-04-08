package ru.vasilev.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vasilev.dto.AuthorDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@OneToMany(mappedBy = "author")
	@JsonManagedReference
	private List<Book> bookList = new ArrayList<>();

	public Author(String name, List<Book> bookList) {
		this.name = name;
		this.bookList = (bookList == null)?new ArrayList<>():bookList;
	}
	
	public void addBook(Book book) {
		bookList.add(book);
		book.setAuthor(this);
	}
	
	public static Author fromAuthorDTO(AuthorDTO author) {
		return new Author(author.getId(), author.getName(), author.getBookList());
	}
}