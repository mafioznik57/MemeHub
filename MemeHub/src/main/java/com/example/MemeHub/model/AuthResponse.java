package com.example.MemeHub.model;


public class AuthResponse {
     public String token;
     public Long userId;

    public AuthResponse(String token, Long userId) {
         this.token = token;
         this.userId = userId;
     }

    public String getToken() {
         return token;
     }
    public void setToken(String token) {
         this.token = token;
     }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}


