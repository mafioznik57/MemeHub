package com.example.MemeHub.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "club_join_request")
@Getter
@Setter
public class ClubJoinRequest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "club_name", nullable = false, length = 32)
    public String clubName;

    @Column(name = "user_email", nullable = false)
    public String userEmail;

    @Column(length = 500)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;

    @Column(name = "decided_by")
    private Long decidedBy;

    @Column(name = "decided_at")
    private Instant decidedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Object getUserEmail() {
        return userEmail;
    }
    public String getClubName(){
        return clubName;
    }
    public String getMessege(){
        return message;
    }
    public RequestStatus getStatus(){
        return status;
    }
    
}
