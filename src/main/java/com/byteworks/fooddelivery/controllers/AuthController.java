package com.byteworks.fooddelivery.controllers;

import com.byteworks.fooddelivery.exception.UserFoundException;
import com.byteworks.fooddelivery.models.User;
import com.byteworks.fooddelivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

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
        } catch (UserFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }

        return ResponseEntity
                .created(URI.create("/api/v1/auth/signup/" + newUser.getId()))
                .body(newUser);
    }

    @RequestMapping("/login")
    public String loginPage() {
//
//        Boolean signedIn = userService.signin(user);
//
//        return ResponseEntity
//                .ok()
//                .body("signed in");
        return "Signed In";
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
