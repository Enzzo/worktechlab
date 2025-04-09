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
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Операции с пользователями, доступные толко администратору")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
	public List<UserDTO> getAllUsers() {
		log.info("Получен GET-запрос на /api/v1/users");
		log.debug("Передача запроса на уровень сервиса");
		return userService.findAll();
	}

	@PostMapping
	@Operation(summary = "Создать пользователя", description = "Создаёт нового пользователя")
	public UserDTO createUser(@Parameter(description = "Объект пользователя", required = true) @RequestBody UserDTO user) {
		log.info("Получен POST-запрос на /api/v1/users");
		log.debug("Передача запроса на уровень сервиса");
		return userService.save(user);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Получить пользователя по ID", description = "Возвращает данные пользователя по идентификатору")
    public UserDTO getUser(@Parameter(description = "Идентификатор пользователя", required = true) @PathVariable Long id) {
		log.info("Получен GET-запрос на /api/v1/users/{}", id);
		log.debug("Передача запроса на уровень сервиса");
		return userService.findById(id);
    }
}
