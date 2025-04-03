package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public  UserServiceImpl(UserDAO userDao,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public void createUser(User user) {
        user.setPassword(makeHashedPassword(user.getPassword()));
        userDao.createUser(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setPassword(makeHashedPassword(user.getPassword()));
        userDao.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByUsername(email);
    }

    private String makeHashedPassword(String password) {
        if (password != null && password.startsWith("{")) {
            return password;
        }
        return "{bcrypt}" + bCryptPasswordEncoder.encode(password);
    }
}
