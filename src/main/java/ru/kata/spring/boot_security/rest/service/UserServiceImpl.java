package ru.kata.spring.boot_security.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.rest.dao.UserDAO;
import ru.kata.spring.boot_security.rest.model.Role;
import ru.kata.spring.boot_security.rest.model.User;
import ru.kata.spring.boot_security.rest.repository.RoleRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDao;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public  UserServiceImpl(UserDAO userDao,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
    public User updateUser(Long id, User user) {
        user.setPassword(makeHashedPassword(user.getPassword()));
        userDao.updateUser(id, user);
        return userDao.getUser(id);
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
        if (password != null && password.startsWith("$2a$10$")) {
            return password;
        }
        return passwordEncoder.encode(password);
    }

    @Override
    public Iterable<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
