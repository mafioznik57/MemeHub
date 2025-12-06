package com.example.MemeHub.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.MemeHub.dto.EventCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location",nullable=false)
    private String location;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;
    
    @Column(name = "event_time", nullable = false)
    private LocalTime eventTime;

    @Column(name = "capacity",nullable=false)
    private int capacity;
    
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventCategory category;
    
    @Column(name = "club_id",nullable=false)
    private Long clubId;
    
    @Column(name = "club_name", nullable = false)
    private String clubName;
    
    @Column(name = "participant_count",nullable = false)
    private Integer participantCount;

    public Event(){
    }

    public Event(Long id,String title,String description,String location,Instant createdAt,LocalDate eventDate,EventCategory category,int capacity,Long clubId,Integer participantCount,String clubName,LocalTime eventTime){
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.eventDate = eventDate;
        this.capacity = capacity;
        this.category = category;
        this.location = location;
        this.clubId = clubId;
        this.participantCount = participantCount;
        this.clubName = clubName;
        this.eventTime = eventTime;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt){
        this.createdAt = createdAt;
    }
    public LocalDate getEventDate(){
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate){
        this.eventDate = eventDate;
    }

    public EventCategory getCategory() {
        return category;
    }
    public void setCategory(EventCategory category) {
        this.category = category;
    }
    
    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public Long getClubId(){
        return clubId;
    }
    public void setClubId(Long clubId){
        this.clubId = clubId;
    }
    public Integer getParticipantCount(){
        return participantCount;
    }
    public void setParticipantCount(Integer participantCount){
        this.participantCount = participantCount;
    }
    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public LocalTime getEventTime() {
        return eventTime;
    }
    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }
}
