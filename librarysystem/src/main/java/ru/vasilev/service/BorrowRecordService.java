package ru.vasilev.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.vasilev.dao.BorrowRecordDAO;
import ru.vasilev.dto.BookDTO;
import ru.vasilev.dto.BorrowRecordDTO;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.entity.Book;
import ru.vasilev.entity.BorrowRecord;
import ru.vasilev.entity.User;

@Service
@Transactional
public class BorrowRecordService {
	BorrowRecordDAO brDAO;
	
	@Autowired
	public BorrowRecordService(BorrowRecordDAO brDAO) {
		this.brDAO = brDAO;
	}

	public BorrowRecordDTO borrowBook(UserDTO user, BookDTO book) {
		BorrowRecord br = new BorrowRecord(User.fromUserDTO(user), Book.fromBookDTO(book));
		return BorrowRecordDTO.fromBorrowRecord(brDAO.save(br));
	}
	
	public BorrowRecordDTO returnBook(Long borrowId) {
		BorrowRecord br = brDAO.findById(borrowId).orElseThrow(() -> new NoSuchElementException("No record found with id: " + borrowId));
		if(br.getReturnDate() == null) {
			br.setReturnDate(LocalDateTime.now());
		}
		
		return BorrowRecordDTO.fromBorrowRecord(brDAO.save(br));
	}
}