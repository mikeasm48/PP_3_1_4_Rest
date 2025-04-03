package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String homeAdmin() {
        return "redirect:/admin/list";
    }

    @GetMapping(value = "list")
    public String listUsers(Principal principal, ModelMap model) {
        User currentUser = userService.getUserByEmail(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", new User());
        return "admin/list";
    }

    @GetMapping(value = "/{id}")
    public String showUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin/list";
    }

    @PostMapping("edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin/list";
    }

    @PostMapping("delete")
    public String deleteUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/list";
    }
}
