package ru.kata.spring.boot_security.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }
    public User getUser(Long id) {
        return  entityManager.find(User.class, id);
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(Long id, User updatedUser) {
        entityManager.merge(updatedUser);
    }

    public void deleteUser(Long id) {
        User userById = getUser(id);
        entityManager.remove(userById);
    }

    public User findByUsername(String email) {
        List<User> list = entityManager.createQuery(
                        "select u from User u where u.email=:email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
