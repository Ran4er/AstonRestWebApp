package ru.aston.dao;

import java.util.List;

import ru.aston.model.User;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
