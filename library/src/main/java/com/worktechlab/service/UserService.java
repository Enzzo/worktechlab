package com.worktechlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worktechlab.dao.UserDaoImpl;
import com.worktechlab.model.User;

@Service
public class UserService implements UserDetailsService{
	private UserDaoImpl userDao;
	private PasswordEncoder encoder;
	
	@Autowired
	public UserService(UserDaoImpl userDao, PasswordEncoder encoder) {
		this.userDao = userDao;
		this.encoder = encoder;
	}

	@Transactional
	public void register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}
}