package ru.vasilev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.vasilev.model.User;
import ru.vasilev.service.UserService;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {
	private final UserService userService;
	private final PasswordEncoder encoder;
	
	@Autowired
	public RegistrationController(UserService userService, PasswordEncoder encoder) {
		this.userService = userService;
		this.encoder = encoder;
	}	
	
	@PostMapping
	public User registerUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userService.save(user);
	}
}
