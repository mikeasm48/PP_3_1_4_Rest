package ru.kata.spring.boot_security.rest.configs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.rest.dao.UserDAOImpl;
import ru.kata.spring.boot_security.rest.model.User;

@Configuration
public class PreloadDb {

    private static final Logger log = LoggerFactory.getLogger(PreloadDb.class);

    @Bean
    CommandLineRunner initDatabase(UserDAOImpl userDAO,
                                   PasswordEncoder passwordEncoder) {
        //Здесь можно, например, менять шифровнные пароли
//        User admin = userDAO.getUser(1L);
//        admin.setPassword(passwordEncoder.encode("admin"));
//        userDAO.updateUser(1L, admin);

//        User mike = userDAO.getUser(2L);
//        mike.setPassword(passwordEncoder.encode("mike"));
//        userDAO.updateUser(2L, mike);

        return args -> { log.info("CAN DO SMTH IN DB BEFORE START: NOTHING"); };
    }

}
