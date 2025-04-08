package ru.vasilev.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vasilev.entity.Book;
import ru.vasilev.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String username;	
	private String password;
	private List<Book> bookList;
	
	public static UserDTO fromUser(User user) {
		return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getBookList());	
	}
}