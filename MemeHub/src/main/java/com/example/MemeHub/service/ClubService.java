package com.example.MemeHub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MemeHub.model.Club;
import com.example.MemeHub.repository.ClubRepository;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public Club AddAClub(Club club) {
        System.out.println("Добавляем новый клуб: " + club.getName());
        return clubRepository.save(club);
    }

    public List<Club> GetAllClubs() {
        System.out.println("Получаем список всех клубов");
        return clubRepository.findAll();
    }


    public Optional<Club> GetClubByName(String name) {
        System.out.println("Ищем клуб с ID: " + name);
        return clubRepository.findByName(name);
    }

     public Optional<Club> GetClubByCategory(String category) {
        System.out.println("Ищем клуб с ID: " + category);
        return clubRepository.findByCategory(category);
    }
    public void DeleteClub(String name) {
        System.out.println("Удаляем клуб с ID: " + name);
        clubRepository.deleteByName(name);
    }
}