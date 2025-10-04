package com.example.MemeHub.repository;

import com.example.MemeHub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query("SELECT u FROM Club u WHERE u.name = ?1")
    Optional<Club> findByName(String name);
    Optional<Club> deleteByName(String name);
}