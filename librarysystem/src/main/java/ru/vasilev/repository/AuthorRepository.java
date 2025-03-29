package ru.vasilev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasilev.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

}