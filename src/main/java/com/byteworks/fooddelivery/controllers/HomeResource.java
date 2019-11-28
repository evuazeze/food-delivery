package com.byteworks.fooddelivery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("")
public class HomeResource {

    @GetMapping("")
    public void welcome(HttpServletResponse httpResponse) {
        try {
            httpResponse.sendRedirect("/docs/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @GetMapping("")
//    public String home() {
//        return ("<h1>Welcome</h1>");
//    }

    @GetMapping("/developer")
    public String developer() {
        return ("<h1>Welcome Developer</h1>");
    }

    @GetMapping("/vendor")
    public String vendor() {
        return ("<h1>Welcome Vendor</h1>");
    }
}
