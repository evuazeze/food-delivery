package com.byteworks.fooddelivery.controllers;

import com.byteworks.fooddelivery.dto.ResponseDto;
import com.byteworks.fooddelivery.exception.UserAlreadyExistsException;
import com.byteworks.fooddelivery.exception.UserDoesNotExistsException;
import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody User user) {

        User newUser;
        try {
            newUser = userService.registerUser(user);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }

        return ResponseEntity
                .created(URI.create("/api/v1/auth/signup/" + newUser.getId()))
                .body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity
                .ok()
                .body(new ResponseDto(200, "User logged In"));
    }

//    @RequestMapping(value="/logout", method = RequestMethod.POST)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login";
//    }
}
