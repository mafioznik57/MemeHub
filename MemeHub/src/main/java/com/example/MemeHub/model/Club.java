package com.example.MemeHub.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;             
    public String category;       
    public String leader;           
    @Column(length = 1000)
    public String description;      
    public int members;            
    public int establishedYear;    
    public String meetingTime;      
    public String email;            

    public LocalDateTime createdAt = LocalDateTime.now(); // Optional: creation timestamp

    // ===== Constructors =====
    public Club() {}

    public Club(String name, String category, String leader, String description, int members,
                int establishedYear, String meetingTime, String email) {
        this.name = name;
        this.category = category;
        this.leader = leader;
        this.description = description;
        this.members = members;
        this.establishedYear = establishedYear;
        this.meetingTime = meetingTime;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLeader() { return leader; }
    public void setLeader(String leader) { this.leader = leader; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getMembers() { return members; }
    public void setMembers(int members) { this.members = members; }

    public int getEstablishedYear() { return establishedYear; }
    public void setEstablishedYear(int establishedYear) { this.establishedYear = establishedYear; }

    public String getMeetingTime() { return meetingTime; }
    public void setMeetingTime(String meetingTime) { this.meetingTime = meetingTime; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}