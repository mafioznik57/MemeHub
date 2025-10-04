package com.example.MemeHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MemeHub.model.ClubMembership;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {
    boolean existsByClubNameAndUserEmail(String clubName, String userEmail);
    boolean existsByClubNameAndUserId(String clubName, Long userId);
}