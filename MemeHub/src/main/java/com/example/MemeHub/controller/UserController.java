package com.example.MemeHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemeHub.repasitory.User;
import com.example.MemeHub.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService memeHubService;

    public UserController(UserService memeHubService) {
        this.memeHubService = memeHubService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return memeHubService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        memeHubService.deleteUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return memeHubService.getAllUserList();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return memeHubService.getById(id);
    }

}
