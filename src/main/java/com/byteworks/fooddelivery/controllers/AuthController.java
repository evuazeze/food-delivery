package com.byteworks.fooddelivery.controllers;

import com.byteworks.fooddelivery.exception.UserFoundException;
import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> register(@RequestBody User user) {

        User newUser;
        try {
            newUser = userService.registerUser(user);
        } catch (UserFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }

        return ResponseEntity
                .created(URI.create("/api/v1/auth/register/" + newUser.getId()))
                .body(newUser);
    }
}
