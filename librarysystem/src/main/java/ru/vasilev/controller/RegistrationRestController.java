package ru.vasilev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/v1/register")
@Tag(name = "Registration", description = "Регистрация нового пользователя. Доступно всем.")
public class RegistrationRestController {
	private final UserService userService;
	private final PasswordEncoder encoder;
	
	@Autowired
	public RegistrationRestController(UserService userService, PasswordEncoder encoder) {
		this.userService = userService;
		this.encoder = encoder;
	}	
	
	@PostMapping
	@Operation(summary = "Регистрация пользователя", description = "Создаёт нового пользователя")
	public UserDTO registerUser(@RequestBody UserDTO user) {
		log.info("Получен POST-запрос на /api/v1/register");
		user.setPassword(encoder.encode(user.getPassword()));
		
		log.debug("Передача запроса на уровень сервиса");
		return userService.save(user);
	}
}
