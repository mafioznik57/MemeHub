package com.example.MemeHub.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MemeHub.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByTitle(String title);
}

