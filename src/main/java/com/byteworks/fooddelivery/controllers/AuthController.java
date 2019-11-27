package com.byteworks.fooddelivery.controllers;

import com.byteworks.fooddelivery.models.Role;
import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String pwd = user.getPassword();
        String encryptPwds = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwds);

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("DEV"));
        user.setRoles(roles);

        userRepository.save(user);
        return "User registered successfully";
    }
}
