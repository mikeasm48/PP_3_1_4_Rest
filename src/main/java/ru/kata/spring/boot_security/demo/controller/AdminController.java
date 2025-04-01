package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

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
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/list";
    }

    @GetMapping(value = "/{id}")
    public String showUser(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/user";
    }

    @GetMapping("new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin/list";
    }

    @GetMapping("edit")
    public String goToEditUser(@RequestParam("id") int id, ModelMap model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "admin/edit";
    }

    @PostMapping("edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/admin/list";
    }

    @PostMapping("delete")
    public String deleteUser(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/list";
    }
}
