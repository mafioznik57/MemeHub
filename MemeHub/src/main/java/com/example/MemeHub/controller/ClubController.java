package com.example.MemeHub.controller;

import com.example.MemeHub.model.Club;
import com.example.MemeHub.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clubInfo")
public class ClubController {

    @Autowired
    ClubService clubService;


    @PostMapping("/AddAClub")
    @PreAuthorize("hasRole('HEAD')")
    @ApiResponse(responseCode = "200", description = "Club added successfully")
    @ApiResponse(responseCode = "404", description = "Club not found")
    public ResponseEntity<Club> AddAClub(@RequestBody Club request){
        try {
            var data = clubService.AddAClub(request);
            return ResponseEntity.ok(data);
        }
        catch (Exception ex){
            throw new RuntimeException("Club cannot be added because:" + ex);
        }
    }

    @PostMapping("/findAClub")
    @Operation(summary = "Find a club by name", description = "Retrieve club information from the database using its name.")
    @ApiResponse(responseCode = "200", description = "Club found successfully")
    @ApiResponse(responseCode = "404", description = "Club not found")
    public ResponseEntity<Club> findAClub(@RequestBody Map<String, String> body) {
        try {
            String request = body.get("name");
            var clubOpt = clubService.GetClubByName(request);

            if (clubOpt.isPresent()) {
                return ResponseEntity.ok(clubOpt.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        catch(Exception ex) {
            throw new RuntimeException("Club cannot be added because:" + ex);
        }
    }
    @DeleteMapping("/terminateTheClub")
    @ApiResponse(responseCode = "200", description = "Club deleted successfully")
    @ApiResponse(responseCode = "404", description = "Club not found")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<?> DeleteAClub(Club request){
        try {
            clubService.DeleteClub(request.name);
            return ResponseEntity.ok("Client was deleted");
        }
        catch(Exception ex){
            return ResponseEntity.badRequest().body("error:" + ex.getMessage());
        }
    }

    @PutMapping("/updateClubData")
    @ApiResponse(responseCode = "200", description = "Club updated successfully")
    @ApiResponse(responseCode = "404", description = "Club not found")
    public ResponseEntity<String> modifyClubData(
            @RequestParam("modificationRequest") String modificationRequest,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("clubName") String clubName) {

        try {
            if (!"changeDescription".equals(modificationRequest)) {
                return ResponseEntity.badRequest().body("Unsupported modificationRequest");
            }

            Optional<Club> existingOpt = clubService.GetClubByName(clubName);
            if (existingOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Club not found: " + clubName);
            }

            Club club = existingOpt.get();
            club.setDescription(description);
            clubService.AddAClub(club);

            return ResponseEntity.ok("Description updated");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("error: " + ex.getMessage());
        }
    }
}
