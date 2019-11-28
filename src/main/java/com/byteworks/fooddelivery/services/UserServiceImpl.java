package com.byteworks.fooddelivery.services;

import com.byteworks.fooddelivery.exception.UserFoundException;
import com.byteworks.fooddelivery.models.Role;
import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) throws UserFoundException{
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent())
            throw new UserFoundException("User already exists. Please login");

        // Encrypt password
        String pwd = user.getPassword();
        String encryptPwds = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwds);

        // Assign Dev Role/Authority on registration
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("DEV"));
        user.setRoles(roles);

        // Exclude the password field from the response
        User savedUser = userRepository.save(user);
        savedUser.setPassword(null);
        return savedUser;
    }
}
