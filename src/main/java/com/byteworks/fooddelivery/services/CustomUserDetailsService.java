package com.byteworks.fooddelivery.services;

import com.byteworks.fooddelivery.models.CustomUserDetails;
import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user.get());
        return userDetails;
    }
}
