package ru.vasilev.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vasilev.entity.Book;
import ru.vasilev.entity.BorrowRecord;
import ru.vasilev.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecordDTO {
	private Long id;
	private User user;
	private Book book;
	private LocalDateTime borrowDate;
	private LocalDateTime returnDate;
	
	public static BorrowRecordDTO fromBorrowRecord(BorrowRecord br) {
		return new BorrowRecordDTO(br.getId(),
		br.getUser(),
		br.getBook(),
		br.getBorrowDate(),
		br.getReturnDate()
		);
	}
}