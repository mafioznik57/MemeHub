package com.example.MemeHub.repository;

import com.example.MemeHub.model.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {
    boolean existsByClubNameAndUserEmail(String clubName, String userEmail);
}