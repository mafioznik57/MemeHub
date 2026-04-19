package com.example.MemeHub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MemeHub.dto.RegisterRequest;
import com.example.MemeHub.repasitory.User;
import com.example.MemeHub.repasitory.UserRepasitory;

@Service
public class UserService {

    private final UserRepasitory userRepasitory;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepasitory userRepasitory,PasswordEncoder passwordEncoder){
        this.userRepasitory = userRepasitory;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUserList(){
        return userRepasitory.findAll();
    }

    public User registerUser(RegisterRequest request){
        Optional<User> existingUser = userRepasitory.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepasitory.save(user);
    }

    
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepasitory.save(user);
    }
        

    public User getById(long id){
        return userRepasitory.findById(id).orElse(null);
    }

    public void deleteUser(long id){
        userRepasitory.deleteById(id);
    }

}
