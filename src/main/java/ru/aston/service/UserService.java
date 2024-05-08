package ru.aston.service;

import java.util.List;

import ru.aston.model.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
