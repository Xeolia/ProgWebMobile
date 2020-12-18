package com.projetweb.reminder.repository;

import com.projetweb.reminder.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projetweb.reminder.Sondage;

import java.util.List;

public interface SondageRepository extends JpaRepository<Sondage, Integer> {
}

