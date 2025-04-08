package ru.vasilev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.dao.UserDAO;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.entity.User;

@Service
@Transactional
public class UserService {
	private final UserDAO userDAO;
	
	@Autowired	
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDTO save(UserDTO user) {
		userDAO.save(User.fromUserDTO(user));
		return user;
	}
	
	public UserDTO findById(Long id) {
		return userDAO.findById(id)
				.map(user -> {
					return UserDTO.fromUser(user);
				})
				.orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
	}
	
	public UserDTO findByUsername(String username) {
		return userDAO.findByUsername(username)
				.map(user -> {
					return UserDTO.fromUser(user);
				})
				.orElseThrow(() -> new NoSuchElementException("User not found with name: " + username));
	}
	
	public List<UserDTO> findAll(){
		List<UserDTO> usersList = new ArrayList<>();
		for(User user : userDAO.findAll()) {
			usersList.add(UserDTO.fromUser(user));
		}
		return usersList;
	}
	
	public void delete(Long id) {
		userDAO.deleteById(id);
	}
}