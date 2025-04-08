package ru.vasilev.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.vasilev.dao.UserDAO;

class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void save_ShouldReturnSavedUser() {
//    	User user = new User("testuser", "password");
//        when(userDAO.save(user)).thenReturn(user);
//
//        User savedUser = userService.save(user);
//        assertEquals("testuser", savedUser.getUsername());
//    }
//
//    @Test
//    void findById_ShouldReturnUser() {
//        User user = new User("testuser", "password");
//        when(userDAO.findById(1L)).thenReturn(Optional.of(user));
//
//        User foundUser = userService.findById(1L);
//        assertEquals("testuser", foundUser.getUsername());
//    }
//
//    @Test
//    void findById_ShouldThrowException_WhenUserNotFound() {
//        when(userDAO.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(NoSuchElementException.class, () -> userService.findById(1L));
//    }
}