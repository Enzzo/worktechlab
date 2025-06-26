package ru.vasilev.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.service.UserService;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private final UserService userService;
	
	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}
	
	@ModelAttribute("newUser")
	public UserDTO newUser() {
		return new UserDTO();
	}

	@GetMapping
	public String registerPage() {
		log.info("Переход на страницу регистрации");
		return "register";
	}
	
	@PostMapping
	public String registerUser(@ModelAttribute UserDTO userToSave) {
		log.info("Выполняется регистрация пользователя");
		if(userService.findByUsername(userToSave.getUsername()) == null) {
			userService.save(userToSave);
			log.info("Регистрация выполнена успешно");
			return "redirect:/";
		}
		else {
			log.error("user ${userToSave.getUsername()} already exists");
			return "register";
		}
		
	}
}