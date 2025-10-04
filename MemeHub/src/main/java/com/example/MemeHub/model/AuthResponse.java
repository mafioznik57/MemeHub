package com.example.MemeHub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AuthResponse {
     public String token;

     public String getToken() {
         return token;
     }
     public void setToken(String token) {
         this.token = token;
     }
}


