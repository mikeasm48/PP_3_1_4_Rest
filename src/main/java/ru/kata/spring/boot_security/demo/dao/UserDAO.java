package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getUsers();
    public User getUser(Long id);
    public void createUser(User user);
    public void updateUser(Long id, User user);
    public void deleteUser(Long id);
    public User findByUsername(String email);
}
