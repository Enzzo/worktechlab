package ru.vasilev.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.model.Book;
import ru.vasilev.model.BorrowRecord;
import ru.vasilev.model.User;
import ru.vasilev.repository.BorrowRecordRepository;

@Service
@Transactional
public class BorrowRecordService {
	BorrowRecordRepository brRepo;
	
	@Autowired
	public BorrowRecordService(BorrowRecordRepository brRepo) {
		this.brRepo = brRepo;
	}

	public BorrowRecord borrowBook(User user, Book book) {
		BorrowRecord br = new BorrowRecord(user, book);
		return brRepo.save(br);
	}
	
	public BorrowRecord returnBook(Long id) {
		BorrowRecord br = brRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No record found with id: " + id));
		if(br.getReturnDate() == null) {
			br.setReturnDate(LocalDateTime.now());
		}
		
		return brRepo.save(br);
	}
}