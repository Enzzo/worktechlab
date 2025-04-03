package ru.vasilev.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.User;
import ru.vasilev.repository.UserRepository;

@Service
@Transactional
public class UserService {
	private final UserRepository userRepo;
	
	@Autowired	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User save(User user) {
		return userRepo.save(user);
	}
	
	public User findById(Long id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
	}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new NoSuchElementException("User not found with name: " + username));
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
}