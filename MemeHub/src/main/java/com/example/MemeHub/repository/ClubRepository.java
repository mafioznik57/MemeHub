package com.example.MemeHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MemeHub.model.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query("SELECT u FROM Club u WHERE u.name = ?1")
    Optional<Club> findByName(String name);
    Optional<Club> findByCategory(String category);
    Optional<Club> deleteByName(String name);
}