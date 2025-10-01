package com.example.MemeHub.service;

import java.util.List;
import java.util.Optional;

import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import com.example.MemeHub.dto.UserCredentials;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.example.MemeHub.dto.RegisterRequest;
import com.example.MemeHub.model.User;
import  com.example.MemeHub.model.AuthResponse;
import com.example.MemeHub.repository.UserRepository;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final SecretKey jwtKey;

    @Value("${jwt.expiration}")
    private long jwtTtlMillis;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${jwt.secret}") String secret) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtKey = Keys.hmacShaKeyFor(secret.getBytes());
    }
    private String generateToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("uid", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(jwtTtlMillis)))
                .signWith(jwtKey, SignatureAlgorithm.HS256)
                .compact();
    }


    public User userJoinRequest(User user){

    }

    public List<User> getAllUserList(){
        return userRepository.findAll();
    }

    public User registerUser(RegisterRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Почта занята");
        }
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new IllegalArgumentException("Неправильный паролль");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public AuthResponse authenticateUser(UserCredentials request){
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Не найден пользователь: " + request.getEmail()));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Неправильный пароль");
        }

        String token = generateToken(user);
        return new AuthResponse(token);
    }
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public User getById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

}
