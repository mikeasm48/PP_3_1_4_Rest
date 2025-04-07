package ru.kata.spring.boot_security.rest.dao;

import ru.kata.spring.boot_security.rest.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getUsers();
    public User getUser(Long id);
    public void createUser(User user);
    public void updateUser(Long id, User user);
    public void deleteUser(Long id);
    public User findByUsername(String email);
}
