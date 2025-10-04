package com.example.MemeHub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemeHub.dto.RegisterRequest;
import com.example.MemeHub.dto.UserCredentials;
import com.example.MemeHub.model.AuthResponse;
import com.example.MemeHub.service.UserService;

import jakarta.validation.Valid;


@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPage(@RequestBody UserCredentials request) {
        try {
            if (userService.getByEmail(request.getEmail()) != null) {
                AuthResponse data = userService.authenticateUser(request);
                return ResponseEntity.ok()
                        .header("Authorization", "Bearer " + data.getToken())
                        .body(data);
            }
            return ResponseEntity.status(401).body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            userService.registerUser(registerRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}

