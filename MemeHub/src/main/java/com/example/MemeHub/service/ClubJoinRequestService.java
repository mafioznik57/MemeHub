package com.example.MemeHub.service;

import com.example.MemeHub.model.ClubJoinRequest;
import com.example.MemeHub.model.RequestStatus;
import com.example.MemeHub.repository.ClubJoinRequestRepository;
import com.example.MemeHub.repository.ClubMembershipRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClubJoinRequestService {

    private final ClubJoinRequestRepository requests;
    private final ClubMembershipRepository memberships;

    public ClubJoinRequestService(ClubJoinRequestRepository requests, ClubMembershipRepository memberships) {
        this.requests = requests;
        this.memberships = memberships;
    }

    @Transactional
    public ClubJoinRequest sendRequest(String clubName, Long userId, String message) {
        if (memberships != null && memberships.existsByClubNameAndUserId(clubName, userId)) {
            throw new IllegalStateException("Уже член клуба");
        }

        if (requests.existsByClubNameAndUserIdAndStatus(clubName, userId, RequestStatus.PENDING)) {
            throw new IllegalStateException("Запрос уже стоит");
        }

        ClubJoinRequest req = new ClubJoinRequest();
        req.setClubName(clubName);
        req.setUserId(userId);
        req.setMessage(message);
        req.setStatus(RequestStatus.PENDING);

        return requests.save(req);
    }

    @Transactional
    public void removeMyRequest(Long requestId, Long userId) {
        ClubJoinRequest req = requests.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Запрос не найден"));
        if (!req.getUserId().equals(userId)) {
            throw new SecurityException("Это не ваш запрос");
        }
        requests.delete(req);
    }
}