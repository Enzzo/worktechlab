package ru.vasilev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasilev.entity.BorrowRecord;

@Repository
public interface BorrowRecordDAO extends JpaRepository<BorrowRecord, Long>{

}