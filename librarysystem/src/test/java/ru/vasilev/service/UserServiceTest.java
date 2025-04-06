package ru.vasilev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.vasilev.model.User;
import ru.vasilev.repository.UserRepository;

class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedUser() {
    	User user = new User("testuser", "password");
        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.save(user);
        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    void findById_ShouldReturnUser() {
        User user = new User("testuser", "password");
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(1L);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void findById_ShouldThrowException_WhenUserNotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.findById(1L));
    }
}