package com.example.MemeHub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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

    public String getClubName() {
        return clubName;
    }

    public String getMassage(){
        return message;
    }


    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    public void setUserEmail(String message){
        this.message = message;
    }


}