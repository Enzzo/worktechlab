package ru.vasilev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasilev.entity.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Long>{

}
