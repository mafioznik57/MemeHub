package com.example.MemeHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemeHub.dto.ClubJoinRequestCreate;
import com.example.MemeHub.service.ClubJoinRequestService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/joinRequest")
public class ClubJoinRequestController {

    @Autowired
    ClubJoinRequestService clubJoinRequestService;

     @PostMapping("/newClubRequest")
     @ApiResponse(responseCode = "200", description = "ClubRequest added successfully")
     @ApiResponse(responseCode = "409", description = "Club was already added")
     public ResponseEntity<Void> joinClub(
             @Valid @RequestBody ClubJoinRequestCreate dto,
             @AuthenticationPrincipal String userEmail) {

         clubJoinRequestService.sendRequest(dto, userEmail);
         return ResponseEntity.ok().build();
     }
}
