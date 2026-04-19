package com.example.MemeHub.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.MemeHub.repasitory.User;
import com.example.MemeHub.repasitory.UserRepasitory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepasitory userRepasitory;

    public CustomUserDetailsService(UserRepasitory userRepasitory) {
        this.userRepasitory = userRepasitory;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepasitory.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return (UserDetails) user;
    }
}