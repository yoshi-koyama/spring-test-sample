package com.raisetech.springtestsample.controller;

import com.raisetech.springtestsample.repository.entity.User;
import com.raisetech.springtestsample.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user";
    }

    @PostMapping("addUser")
    public String addUser(@RequestParam("name") String name) {
        userService.saveUser(name);
        return "redirect:/users";
    }
}
