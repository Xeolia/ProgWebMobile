package com.projetweb.reminder.repository;

import com.projetweb.reminder.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);
}

