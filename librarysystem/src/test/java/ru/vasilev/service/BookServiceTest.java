package ru.vasilev.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import ru.vasilev.dao.AuthorDAO;
import ru.vasilev.dao.BookDAO;

class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    @Mock
    private AuthorDAO authorDAO;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void save_ShouldReturnSavedBook() {
//        Author author = new Author("Test Author", null);
//        Book book = new Book("Test Book", author);
//        when(authorDAO.findById(1L)).thenReturn(Optional.of(author));
//        when(bookDAO.save(book)).thenReturn(book);
//
//        Book savedBook = bookService.save("Test Book", 1L);
//        assertEquals("Test Book", savedBook.getTitle());
//    }

//    @Test
//    void findById_ShouldReturnBook() {
//        Book book = new Book("Test Book", null);
//        when(bookDAO.findById(1L)).thenReturn(Optional.of(book));
//
//        Book foundBook = bookService.findById(1L);
//        assertEquals("Test Book", foundBook.getTitle());
//    }

//    @Test
//    void findById_ShouldThrowException_WhenBookNotFound() {
//        when(bookDAO.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(NoSuchElementException.class, () -> bookService.findById(1L));
//    }
}