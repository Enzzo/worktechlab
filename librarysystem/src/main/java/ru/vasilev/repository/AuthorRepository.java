package ru.vasilev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasilev.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	Optional<Author> findByName(String name);
}