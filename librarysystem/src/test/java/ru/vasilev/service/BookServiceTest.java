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
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import ru.vasilev.dao.AuthorDAO;
import ru.vasilev.dao.BookDAO;
import ru.vasilev.entity.Author;
import ru.vasilev.entity.Book;

@ActiveProfiles("dev")
class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @Mock
    private AuthorDAO authorDAO;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedBook() {
        Author author = new Author("Test Author", null);
        Book book = new Book("Test Book", author);
        when(authorDAO.findById(1L)).thenReturn(Optional.of(author));
        when(bookDAO.save(book)).thenReturn(book);

        Book savedBook = Book.fromBookDTO(bookService.save("Test Book", 1L));
        assertEquals("Test Book", savedBook.getTitle());
    }

    @Test
    void findById_ShouldReturnBook() {
        Book book = new Book("Test Book", null);
        when(bookDAO.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = Book.fromBookDTO(bookService.findById(1L));
        assertEquals("Test Book", foundBook.getTitle());
    }

    @Test
    void findById_ShouldThrowException_WhenBookNotFound() {
        when(bookDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> bookService.findById(1L));
    }
}