package com.example.MemeHub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.MemeHub.dto.RegisterRequest;
import com.example.MemeHub.service.UserService;

@Controller
public class AuthController {

    UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterRequest registerRequest, Model model) {
        System.out.println("Получены данные: " + registerRequest.getEmail());
        
        try {
            userService.registerUser(registerRequest);
            model.addAttribute("success", true);
            model.addAttribute("registerRequest", new RegisterRequest());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("registerRequest", registerRequest);
        }
        
        return "register";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

}