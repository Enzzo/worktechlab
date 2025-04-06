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

import ru.vasilev.model.Author;
import ru.vasilev.repository.AuthorRepository;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepo;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedAuthor() {
        Author author = new Author("Test Author", null);
        when(authorRepo.save(author)).thenReturn(author);

        Author savedAuthor = authorService.save(author);
        assertEquals("Test Author", savedAuthor.getName());
    }

    @Test
    void findById_ShouldReturnAuthor() {
        Author author = new Author("Test Author", null);
        when(authorRepo.findById(1L)).thenReturn(Optional.of(author));

        Author foundAuthor = authorService.findById(1L);
        assertEquals("Test Author", foundAuthor.getName());
    }

    @Test
    void findById_ShouldThrowException_WhenAuthorNotFound() {
        when(authorRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> authorService.findById(1L));
    }
}