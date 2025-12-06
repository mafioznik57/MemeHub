package com.example.MemeHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemeHub.dto.ClubJoinRequestCreate;
import com.example.MemeHub.model.ClubJoinRequest;
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
     public ResponseEntity<Void> joinClub(@Valid @RequestBody ClubJoinRequestCreate dto) {

         clubJoinRequestService.sendRequest(dto);
         return ResponseEntity.ok().build();
     }
     @GetMapping("/viewRequests/{headEmail}")
     public ResponseEntity<?> viewRequests(@PathVariable String headEmail) {
         try {
             var data = clubJoinRequestService.viewClubJoinRequest(headEmail);
             return ResponseEntity.ok(data);
         } catch (Exception ex) {
             return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
         }
     }

    @PostMapping("/checkResponse")
    public ResponseEntity<?> respondToRequest(ClubJoinRequest request, boolean accept) {
        try {

            var result = clubJoinRequestService.addClubResponse(request, accept);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }

}
