package ru.vasilev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import ru.vasilev.dao.BorrowRecordDAO;
import ru.vasilev.dto.BookDTO;
import ru.vasilev.dto.BorrowRecordDTO;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.entity.Book;
import ru.vasilev.entity.BorrowRecord;
import ru.vasilev.entity.User;

@Slf4j
@Service
@Transactional
public class BorrowRecordService {
	BorrowRecordDAO brDAO;
	
	@Autowired
	public BorrowRecordService(BorrowRecordDAO brDAO) {
		this.brDAO = brDAO;
	}
	
	public List<BorrowRecordDTO> findAll(){
		log.info("Начало выполнения операции findAll в сервисном слое.");
		List<BorrowRecordDTO> brList = new ArrayList<>();
		for(BorrowRecord br : brDAO.findAll()) {
			brList.add(BorrowRecordDTO.fromBorrowRecord(br));
		}
		return brList;
	}

	public BorrowRecordDTO borrowBook(UserDTO user, BookDTO book) {
		log.info("Начало выполнения операции borrowBook в сервисном слое.");
		BorrowRecord br = new BorrowRecord(User.fromUserDTO(user), Book.fromBookDTO(book));
		return BorrowRecordDTO.fromBorrowRecord(brDAO.save(br));
	}
	
	public BorrowRecordDTO returnBook(Long borrowId) {
		log.info("Начало выполнения операции returnBook в сервисном слое.");
		BorrowRecord br = brDAO.findById(borrowId).orElseThrow(() -> new NoSuchElementException("No record found with id: " + borrowId));
		
		if(br.getReturnDate() == null) {
			br.setReturnDate(LocalDateTime.now());
		}
		
		return BorrowRecordDTO.fromBorrowRecord(brDAO.save(br));
	}
}