package com.worktechlab.dao;

import java.util.Optional;

import com.worktechlab.model.User;

public interface UserDao {
	void save(User user);
	Optional<User> findByUsername(String username);
}