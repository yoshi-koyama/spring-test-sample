package com.raisetech.springtestsample.controller;

import com.raisetech.springtestsample.repository.entity.User;
import com.raisetech.springtestsample.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> users() {
         return userService.findAllUsers();
    }
}
