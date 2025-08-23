package com.pgmanagement.hostels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String root() {
        // Serve login page at root. Your app lives at /index.html (main UI).
        return "redirect:/login.html";
    }
}
