package com.example.MemeHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubJoinRequestCreate {

    @NotBlank(message = "Club name is required")
    @Size(max = 32, message = "Club name must be at most 32 characters")
    private String clubName;

    @Size(max = 500, message = "Message must be at most 500 characters")
    private String message;

    private String userEmail;

    private String headEmail;

    public String getUserEmail(){
        return userEmail;
    }

     public String getHeadEmail(){
        return headEmail;
    }

    public String getClubName() {
        return clubName;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    public void setHEadEmail(String headEmail){
        this.headEmail = headEmail;
    }


}