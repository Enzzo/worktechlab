package ru.vasilev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ru.vasilev.model.Author;
import ru.vasilev.model.Book;
import ru.vasilev.repository.AuthorRepository;
import ru.vasilev.repository.BookRepository;

class BookServiceTest {

    @Mock
    private BookRepository bookRepo;

    @Mock
    private AuthorRepository authorRepo;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void save_ShouldReturnSavedBook() {
//        Author author = new Author("Test Author", null);
//        Book book = new Book("Test Book", author);
//        when(authorRepo.findById(1L)).thenReturn(Optional.of(author));
//        when(bookRepo.save(book)).thenReturn(book);
//
//        Book savedBook = bookService.save("Test Book", 1L);
//        assertEquals("Test Book", savedBook.getTitle());
//    }

//    @Test
//    void findById_ShouldReturnBook() {
//        Book book = new Book("Test Book", null);
//        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
//
//        Book foundBook = bookService.findById(1L);
//        assertEquals("Test Book", foundBook.getTitle());
//    }

//    @Test
//    void findById_ShouldThrowException_WhenBookNotFound() {
//        when(bookRepo.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(NoSuchElementException.class, () -> bookService.findById(1L));
//    }
}