package com.byteworks.fooddelivery.controllers;

import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        User newUser = userService.registerUser(user);

        return ResponseEntity
                .created(URI.create("/api/v1/auth/register/" + newUser.getId()))
                .body(newUser);
    }
}
