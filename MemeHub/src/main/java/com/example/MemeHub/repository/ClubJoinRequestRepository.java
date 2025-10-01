package com.example.MemeHub.repository;


import com.example.MemeHub.model.ClubJoinRequest;
import com.example.MemeHub.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubJoinRequestRepository extends JpaRepository<ClubJoinRequest, Long> {

    boolean existsByClubNameAndUserIdAndStatus(String clubName, Long userId, RequestStatus status);

    List<ClubJoinRequest> findByClubNameAndStatusOrderByCreatedAtAsc(String clubName, RequestStatus status);
}
