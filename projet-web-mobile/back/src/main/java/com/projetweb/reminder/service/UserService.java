package com.projetweb.reminder.service;

import com.projetweb.reminder.User;
import com.projetweb.reminder.repository.UserRepository;
import com.projetweb.reminder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurer;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfigurer passwordEncoder;

    @Autowired
    private JwtUtil TokenUtil;

    public List<User> getUser()
    {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username)
    {
        return userRepository.getUserByUsername(username);
    }
    public String getUsernameFromToken(String token)
    {
        token = token.substring(7);
        return TokenUtil.extractUsername(token);
    }
}
