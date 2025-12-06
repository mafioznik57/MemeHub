package com.example.MemeHub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.MemeHub.model.EventRequest;
import com.example.MemeHub.repository.EventRequestRepository;

@Service
public class EventRequestService {

    private final EventRequestRepository eventRequestRepository;

    public EventRequestService(EventRequestRepository eventRequestRepository) {
        this.eventRequestRepository = eventRequestRepository;
    }

    public EventRequest createEventRequest(EventRequest eventRequest) {
        return eventRequestRepository.save(eventRequest);
    }

    public void deleteEventRequest(Long id) {
        eventRequestRepository.deleteById(id);
    }

    public List<EventRequest> getAllEventRequests() {
        return eventRequestRepository.findAll();
    }

    public Optional<EventRequest> getEventRequestById(Long id) {
        return eventRequestRepository.findById(id);
    }

    public EventRequest updateEventRequest(EventRequest eventRequest) {
        return eventRequestRepository.save(eventRequest);
    }

    // Исправленный метод - возвращает список
    public List<EventRequest> getEventRequestsByUserId(Long userId) {
        return eventRequestRepository.findByUserId(userId);
    }
    
    // Добавьте этот метод если нужно
    public List<EventRequest> getEventRequestsByEventId(Long eventId) {
        return eventRequestRepository.findByEventId(eventId);
    }
}