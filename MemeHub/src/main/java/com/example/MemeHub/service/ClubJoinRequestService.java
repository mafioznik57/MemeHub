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
    public ClubJoinRequest sendRequest(String clubName, String email, String message) {
        if (memberships != null && memberships.existsByClubNameAndUserEmail(clubName, email)) {
            throw new IllegalStateException("Уже член клуба");
        }

        if (requests.existsByClubNameAndUserEmailAndStatus(clubName, email, RequestStatus.PENDING)) {
            throw new IllegalStateException("Запрос уже стоит");
        }

        ClubJoinRequest req = new ClubJoinRequest();
        req.setClubName(clubName);
        req.setUserEmail(email);
        req.setMessage(message);
        req.setStatus(RequestStatus.PENDING);

        return requests.save(req);
    }

    @Transactional
    public void removeMyRequest(Long requestId, String email) {
        ClubJoinRequest req = requests.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Запрос не найден"));
        if (!req.getUserEmail().equals(email)) {
            throw new SecurityException("Это не ваш запрос");
        }
        requests.delete(req);
    }
}