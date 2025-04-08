package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import ru.vasilev.entity.BorrowRecord;

public interface BorrowRecordDAO {
	Optional<BorrowRecord> findById(Long id);
	List<BorrowRecord> findAll();
	BorrowRecord save(BorrowRecord br);
	void deleteById(Long id);
	BorrowRecord delete(BorrowRecord br);
}