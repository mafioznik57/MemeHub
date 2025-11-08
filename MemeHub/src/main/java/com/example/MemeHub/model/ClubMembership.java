package com.example.MemeHub.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "club_memberships")
@Getter
@Setter
public class ClubMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "club_name", nullable = false)
    private String clubName;

    @Column(name = "email", nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String role = "MEMBER";

    @Column(nullable = false)
    private String status = "ACTIVE";

    @Column(name = "joined_at", nullable = false, updatable = false)
    private Instant joinedAt = Instant.now();

}