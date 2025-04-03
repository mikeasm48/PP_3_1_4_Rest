package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * Отображает страницу входа.
     *
     * @return Строка с именем представления "security/login".
     */
    @GetMapping("login")
    public String loginPage() {
        return "security/login";
    }

}