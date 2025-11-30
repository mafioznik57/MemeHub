package com.example.MemeHub.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MemeHub.model.ClubJoinRequest;
import com.example.MemeHub.model.RequestStatus;

public interface ClubJoinRequestRepository extends JpaRepository<ClubJoinRequest, Long> {

    boolean existsByClubNameAndUserEmailAndStatus(String clubName, String userEmail, RequestStatus status);

    List<ClubJoinRequest> findByClubNameAndStatusOrderByCreatedAtAsc(String clubName, RequestStatus status);
    List<ClubJoinRequest> findByHeadEmail(String headEmail);
}
