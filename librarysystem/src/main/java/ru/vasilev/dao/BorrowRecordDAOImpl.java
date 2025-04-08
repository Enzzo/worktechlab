package ru.vasilev.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ru.vasilev.entity.BorrowRecord;

@Repository
public class BorrowRecordDAOImpl implements BorrowRecordDAO{

	@Override
	public Optional<BorrowRecord> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<BorrowRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BorrowRecord save(BorrowRecord br) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BorrowRecord delete(BorrowRecord br) {
		// TODO Auto-generated method stub
		return null;
	}

}
