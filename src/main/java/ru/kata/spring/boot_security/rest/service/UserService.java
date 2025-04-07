package ru.kata.spring.boot_security.rest.service;

import ru.kata.spring.boot_security.rest.model.Role;
import ru.kata.spring.boot_security.rest.model.User;
import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUser(Long id);
    public void createUser(User user);
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);
    public User getUserByEmail(String email);
    public Iterable<Role> findAllRoles();
}
