package ru.vasilev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.entity.User;
import ru.vasilev.repository.UserRepository;

@Slf4j
@Service
@Transactional
public class UserService {
	private final UserRepository userRepo;
	
	@Autowired	
	public UserService(UserRepository userDAO) {
		this.userRepo = userDAO;
	}

	public UserDTO save(UserDTO userDTO) {
		log.info("Начало выполнения операции save в сервисном слое.");
		User user = userRepo.save(User.fromUserDTO(userDTO));
		log.debug("Пользователь {} успешно добавлен в базу", user.getUsername());
		return UserDTO.fromUser(user);
	}
	
	public UserDTO findById(Long id) {
		log.info("Начало выполнения операции findById в сервисном слое.");
		return userRepo.findById(id)
				.map(user -> {
					return UserDTO.fromUser(user);
				})
				.orElseThrow(() -> {
					log.error("Ошибка при выполнении операции findById({})", id);
					return new NoSuchElementException("User not found with id: " + id);
				});
	}
	
	public UserDTO findByUsername(String username) {
		log.info("Начало выполнения операции findByUsername в сервисном слое.");
		return userRepo.findByUsername(username)
				.map(user -> {
					return UserDTO.fromUser(user);
				})
				.orElseThrow(() -> {
					log.error("Ошибка при выполнении операции findByUsername({})", username);
					return new NoSuchElementException("User not found with name: " + username);
				});
	}
	
	public List<UserDTO> findAll(){
		log.info("Начало выполнения операции findAll в сервисном слое.");
		List<UserDTO> usersList = new ArrayList<>();
		for(User user : userRepo.findAll()) {
			usersList.add(UserDTO.fromUser(user));
		}
		return usersList;
	}
	
	public void delete(Long id) {
		log.info("Начало выполнения операции delete в сервисном слое.");
		userRepo.deleteById(id);
	}
}