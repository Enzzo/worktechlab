package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import ru.vasilev.entity.User;

public interface UserDAO {
	Optional<User> findById(Long id);
	Optional<User> findByUsername(String username);
	List<User> findAll();
	User save(User user);
	void deleteById(Long id);
	void delete(User user);
}
