package ru.kata.spring.boot_security.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.rest.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
}
