package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "API is running! Use POST /users to create a user.";
    }

    @PostMapping
    public String createUser(@RequestParam String username) {
        userService.createUser(username);
        return "User created!";
    }
}