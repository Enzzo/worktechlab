package ru.vasilev.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vasilev.dto.BorrowRecordDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	private LocalDateTime borrowDate;
	private LocalDateTime returnDate;
	
	public BorrowRecord(User user, Book book) {
		this.user = user;
		this.book = book;
		borrowDate = LocalDateTime.now();
	}
	public static BorrowRecord fromBorrowRecordDTO(BorrowRecordDTO brDTO) {
		return new BorrowRecord(brDTO.getId(), 
				brDTO.getUser(), 
				brDTO.getBook(),
				brDTO.getBorrowDate(),
				brDTO.getReturnDate()
				);
	}
}