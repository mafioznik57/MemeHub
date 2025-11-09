package com.example.MemeHub.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private  String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @Column(name = "event_date", nullable = false)
    private Instant eventDate;

    public Event(){

    }

    public Event(Long id,String title,String description,Instant createdAt,Instant eventDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.eventDate = eventDate;
    }

    public Long getEventId(){
        return id;
    }

    public void setEventId(Long id){
        this.id = id;
    }

    public String getEventTitle(){
        return title;
    }

    public void setEventTitle(String title){
        this.title = title;
    }

    public String getEventDescription(){
        return description;
    }

    public void setEventDescription(String description){
        this.description = description;
    }

    public Instant getEventcreatedAt(){
        return createdAt;
    }

    public void setEventcreatedAt(Instant createdAt){
        this.createdAt = createdAt;
    }
    public Instant getEventDate(){
        return eventDate;
    }

    public void setEventDate(Instant eventDate){
        this.eventDate = eventDate;
    }

}
