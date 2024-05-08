package ru.aston.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.dao.UserDAO;
import ru.aston.model.User;
import ru.aston.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser() {
        User user = new User("testUser", "testPassword");
        userService.addUser(user);
        verify(userDAO, times(1)).addUser(user);
    }

    @Test
    void getUserById() {
        User expectedUser = new User("testUser", "testPassword");
        when(userDAO.getUserById(1)).thenReturn(expectedUser);
        User actualUser = userService.getUserById(1);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getAllUsers() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("testUser1", "testPassword1"));
        expectedUsers.add(new User("testUser2", "testPassword2"));
        when(userDAO.getAllUsers()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.getAllUsers();
        assertEquals(expectedUsers.size(), actualUsers.size());
    }

    @Test
    void updateUser() {
        User user = new User("testUser", "testPassword");
        user.setId(1);
        userService.updateUser(user);
        verify(userDAO, times(1)).updateUser(user);
    }

    @Test
    void deleteUser() {
        userService.deleteUser(1);
        verify(userDAO, times(1)).deleteUser(1);
    }
}
