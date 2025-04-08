package ru.vasilev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.vasilev.dao.UserDAO;
import ru.vasilev.entity.User;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Операции с пользователями")
public class UserController {

	private final UserDAO userDAO;

	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping
	@Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	@PostMapping
	@Operation(summary = "Создать пользователя", description = "Создаёт нового пользователя")
	public User createUser(@Parameter(description = "Объект пользователя", required = true) @RequestBody User user) {
		return userDAO.save(user);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Получить пользователя по ID", description = "Возвращает данные пользователя по идентификатору")
    public User getUser(@Parameter(description = "Идентификатор пользователя", required = true) @PathVariable Long id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
