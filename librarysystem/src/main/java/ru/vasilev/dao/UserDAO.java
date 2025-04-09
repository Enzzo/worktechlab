package ru.vasilev.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasilev.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}