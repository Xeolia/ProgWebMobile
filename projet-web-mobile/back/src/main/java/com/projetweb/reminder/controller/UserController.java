package com.projetweb.reminder.controller;

import com.projetweb.reminder.User;
import com.projetweb.reminder.service.UserService;
import com.projetweb.reminder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil Tokenutil;

    @GetMapping("/user")
    @ResponseBody
    public User getUserByUsername(@RequestHeader("Authorization") Map<String, String> headers)
    {
        String username = userService.getUsernameFromToken(headers.get("authorization"));
        User user =  userService.getUserByUsername(username);
        return user;
    }

}
