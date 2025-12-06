package com.example.MemeHub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemeHub.dto.EventRequestCreate;
import com.example.MemeHub.model.EventRequest;
import com.example.MemeHub.model.RequestStatus;
import com.example.MemeHub.service.EventRequestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/test")
public class EventRequestController {

    private final EventRequestService eventRequestService;

    public EventRequestController(EventRequestService eventRequestService) {
        this.eventRequestService = eventRequestService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new event request")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Event request created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<EventRequest> createEventRequest(
            @Valid @RequestBody EventRequestCreate requestDto) {
        
        EventRequest eventRequest = new EventRequest();
        eventRequest.setTitle(requestDto.getTitle());
        eventRequest.setUserId(requestDto.getUserId());
        eventRequest.setEventId(requestDto.getEventId());
        
        EventRequest createdRequest = eventRequestService.createEventRequest(eventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all event requests")
    public ResponseEntity<List<EventRequest>> getAllEventRequests() {
        List<EventRequest> requests = eventRequestService.getAllEventRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event request by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Event request found"),
        @ApiResponse(responseCode = "404", description = "Event request not found")
    })
    public ResponseEntity<EventRequest> getEventRequestById(@PathVariable Long id) {
        Optional<EventRequest> request = eventRequestService.getEventRequestById(id);
        return request.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get event requests by user ID")
    public ResponseEntity<?> getEventRequestsByUserId(@PathVariable Long userId) {
        List<EventRequest> requests = eventRequestService.getEventRequestsByUserId(userId);
        
        if (!requests.isEmpty()) {
            return ResponseEntity.ok(requests); 
        } else {
            return ResponseEntity.ok().body("No requests found for user with ID: " + userId);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update event request")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Event request updated successfully"),
        @ApiResponse(responseCode = "404", description = "Event request not found")
    })
    public ResponseEntity<EventRequest> updateEventRequest(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request) {
        
        // Проверяем, существует ли запрос
        Optional<EventRequest> existingRequest = eventRequestService.getEventRequestById(id);
        if (existingRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        request.setId(id); // Убедимся, что ID сохраняется
        EventRequest updatedRequest = eventRequestService.updateEventRequest(request);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete event request")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Event request deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Event request not found")
    })
    public ResponseEntity<Void> deleteEventRequest(@PathVariable Long id) {
        Optional<EventRequest> request = eventRequestService.getEventRequestById(id);
        if (request.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        eventRequestService.deleteEventRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update request status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status updated successfully"),
        @ApiResponse(responseCode = "404", description = "Event request not found")
    })
    public ResponseEntity<EventRequest> updateRequestStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        
        Optional<EventRequest> existingRequest = eventRequestService.getEventRequestById(id);
        if (existingRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        EventRequest request = existingRequest.get();
        try {
            RequestStatus newStatus = RequestStatus.valueOf(status.toUpperCase());
            request.setStatus(newStatus);
            EventRequest updatedRequest = eventRequestService.updateEventRequest(request);
            return ResponseEntity.ok(updatedRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}