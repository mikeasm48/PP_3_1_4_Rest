package ru.kata.spring.boot_security.rest.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.rest.model.Role;
import ru.kata.spring.boot_security.rest.model.User;
import javax.servlet.http.HttpSession;
import java.util.Objects;


//CRUD operations Implement
@Service
public class AppServiceImpl implements AppService {

    @Override
    public String getPage(Model model, HttpSession session, Authentication auth) {
        if (Objects.isNull(auth)) {
            model.addAttribute("authenticatedName", session.getAttribute("authenticatedName"));
            session.removeAttribute("authenticatedName");

            model.addAttribute("authenticationException", session.getAttribute("authenticationException"));
            session.removeAttribute("authenticationException");

            return "login-page";
        }

        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        if (user.hasRole(1)) {
            return "main-page";
        }

        if (user.hasRole(2)) {
            return "user-page";
        }

        return "access-denied-page";
    }
}
