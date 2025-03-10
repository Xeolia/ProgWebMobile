package com.projetweb.reminder.repository;

import com.projetweb.reminder.User;
import com.projetweb.reminder.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findByTypeAndChoixAndReference(String type, String choix, int id);
}