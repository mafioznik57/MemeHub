package com.example.MemeHub.repository;

import com.example.MemeHub.model.EventRequest;
import com.example.MemeHub.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {
    
    List<EventRequest> findByUserId(Long userId);
    
    List<EventRequest> findByEventId(Long eventId);
    
    List<EventRequest> findByUserIdAndStatus(Long userId, RequestStatus status);
    
    List<EventRequest> findByEventIdAndStatus(Long eventId, RequestStatus status);
}