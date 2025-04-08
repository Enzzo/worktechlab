package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ru.vasilev.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}
}