package com.example.MemeHub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MemeHub.model.Event;
import com.example.MemeHub.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}

