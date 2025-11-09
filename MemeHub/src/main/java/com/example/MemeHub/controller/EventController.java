package com.example.MemeHub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemeHub.model.Event;
import com.example.MemeHub.service.EventService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    @ApiResponse(responseCode = "200", description = "Event added successfully")
    @ApiResponse(responseCode = "404", description = "Event not added")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event savedEvent = eventService.addEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    @GetMapping("/all")
    @ApiResponse(responseCode = "200", description = "Events retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No events found")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/find")
    @ApiResponse(responseCode = "200", description = "Club found successfully")
    @ApiResponse(responseCode = "404", description = "Club not found")
    @PreAuthorize("hasAnyRole('ADMIN','HEAD')")
    public ResponseEntity<Event> getEventByTitle(@RequestParam String title) {
        Optional<Event> eventOpt = eventService.getEventByTitle(title);
        return eventOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @ApiResponse(responseCode = "200", description = "Club deleted successfully")
    @ApiResponse(responseCode = "404", description = "Club not found")
    @PreAuthorize("hasAnyRole('ADMIN','HEAD')")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }
}

