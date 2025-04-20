package ru.vasilev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dao.UserDAO;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.entity.User;

@Slf4j
@Service
@Transactional
public class UserService {
	private final UserDAO userDAO;
	
	@Autowired	
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDTO save(UserDTO userDTO) {
		log.info("Начало выполнения операции save в сервисном слое.");
		User user = userDAO.save(User.fromUserDTO(userDTO));
		log.debug("Пользователь {} успешно добавлен в базу", user.getUsername());
		return UserDTO.fromUser(user);
	}
	
	public UserDTO findById(Long id) {
		log.info("Начало выполнения операции findById в сервисном слое.");
		return userDAO.findById(id)
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
		return userDAO.findByUsername(username)
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
		for(User user : userDAO.findAll()) {
			usersList.add(UserDTO.fromUser(user));
		}
		return usersList;
	}
	
	public void delete(Long id) {
		log.info("Начало выполнения операции delete в сервисном слое.");
		userDAO.deleteById(id);
	}
}