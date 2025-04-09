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

import ru.vasilev.dao.UserDAO;
import ru.vasilev.dto.UserDTO;
import ru.vasilev.entity.User;

@ActiveProfiles("dev")
class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedUser() {
    	User user = new User("testuser", "password");
        when(userDAO.save(user)).thenReturn(user);

        User savedUser = User.fromUserDTO(userService.save(UserDTO.fromUser(user)));
        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    void findById_ShouldReturnUser() {
        User user = new User("testuser", "password");
        when(userDAO.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = User.fromUserDTO(userService.findById(1L));
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void findById_ShouldThrowException_WhenUserNotFound() {
        when(userDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.findById(1L));
    }
}