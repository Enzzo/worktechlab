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

import ru.vasilev.dao.AuthorDAO;
import ru.vasilev.dto.AuthorDTO;
import ru.vasilev.entity.Author;

class AuthorServiceTest {

    @Mock
    private AuthorDAO authorDAO;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void save_ShouldReturnSavedAuthor() {
//        Author author = new Author("Test Author", null);
//        when(authorDAO.save(author)).thenReturn(author);
//
//        Author savedAuthor = Author.fromAuthorDTO(authorService.save(AuthorDTO.fromAuthor(author)));
//        assertEquals("Test Author", savedAuthor.getName());
//    }
//
//    @Test
//    void findById_ShouldReturnAuthor() {
//        Author author = new Author("Test Author", null);
//        when(authorDAO.findById(1L)).thenReturn(Optional.of(author));
//
//        Author foundAuthor = Author.fromAuthorDTO(authorService.findById(1L));
//        assertEquals("Test Author", foundAuthor.getName());
//    }
//
//    @Test
//    void findById_ShouldThrowException_WhenAuthorNotFound() {
//        when(authorDAO.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(NoSuchElementException.class, () -> authorService.findById(1L));
//    }
}