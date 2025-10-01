package com.example.MemeHub.controller;

import com.example.MemeHub.dto.UserCredentials;
import com.example.MemeHub.model.AuthResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.MemeHub.dto.RegisterRequest;
import com.example.MemeHub.service.UserService;


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
                return ResponseEntity.ok(data);
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

