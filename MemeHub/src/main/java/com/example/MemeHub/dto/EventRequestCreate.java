package com.example.MemeHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventRequestCreate {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Event ID is required")
    private Long eventId;

    public EventRequestCreate() {}
    
    public EventRequestCreate(String title, Long userId, Long eventId) {
        this.title = title;
        this.userId = userId;
        this.eventId = eventId;
    }

    // Getters и Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}