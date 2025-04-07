package ru.kata.spring.boot_security.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.rest.service.AppService;
import ru.kata.spring.boot_security.rest.service.AppServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
public class RootController {

private final AppService appService;

    @Autowired
    public RootController(AppServiceImpl appService) {
        this.appService = appService;
    }


    @GetMapping({"", "/"})
    public String home(Model model, HttpSession session, @Nullable Authentication auth) {
        return appService.getPage(model, session, auth);
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied-page";
    }
}
