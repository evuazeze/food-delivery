package com.byteworks.fooddelivery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeResource {

    @GetMapping("")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/developer")
    public String developer() {
        return ("<h1>Welcome Developer</h1>");
    }

    @GetMapping("/vendor")
    public String vendor() {
        return ("<h1>Welcome Vendor</h1>");
    }
}
