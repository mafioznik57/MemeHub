package com.example.MemeHub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MemeHub.dto.ClubJoinRequestCreate;
import com.example.MemeHub.model.ClubJoinRequest;
import com.example.MemeHub.model.ClubMembership;
import com.example.MemeHub.model.RequestStatus;
import com.example.MemeHub.repository.ClubJoinRequestRepository;
import com.example.MemeHub.repository.ClubMembershipRepository;

import jakarta.transaction.Transactional;

@Service
public class ClubJoinRequestService {

    private final ClubJoinRequestRepository requests;
    private final ClubMembershipRepository memberships;

    public ClubJoinRequestService(ClubJoinRequestRepository requests, ClubMembershipRepository memberships) {
        this.requests = requests;
        this.memberships = memberships;
    }

    public ClubJoinRequest sendRequest(ClubJoinRequestCreate request) {


        if (memberships != null && memberships.existsByClubNameAndUserEmail(request.getClubName(), request.getUserEmail())) {
            throw new IllegalStateException("Уже член клуба");
        }

        if (requests.existsByClubNameAndUserEmailAndStatus(request.getClubName(),  request.getUserEmail(), RequestStatus.PENDING)) {
            throw new IllegalStateException("Запрос уже стоит");
        }

        ClubJoinRequest req = new ClubJoinRequest();
        req.setClubName(request.getClubName());
        req.setUserEmail( request.getUserEmail());
        req.setMessage(request.getMessage());
        req.setHeadEmail(request.getHeadEmail());
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

    public List<ClubJoinRequest> viewClubJoinRequest(String headEmail) {
        return requests.findByHeadEmail(headEmail);
    }

    public ClubJoinRequest addClubResponse(ClubJoinRequest request, boolean accept) {
        if (accept) {
            ClubMembership membership = new ClubMembership();
            membership.setClubName(request.getClubName());
            membership.setUserEmail(request.getUserEmail());
            memberships.save(membership);
            request.setStatus(RequestStatus.APPROVED);
        } else {
            request.setHeadEmail("null");
            request.setStatus(RequestStatus.DECLINED);
        }
        return requests.save(request);
    }

}