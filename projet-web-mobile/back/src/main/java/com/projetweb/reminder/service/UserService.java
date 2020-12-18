package com.projetweb.reminder.service;

import com.projetweb.reminder.SecurityConfigurer;
import com.projetweb.reminder.User;
import com.projetweb.reminder.repository.UserRepository;
import com.projetweb.reminder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public boolean createUser(String username, String password, String name, String email,String ville, String adresse,String pays,String codePostal)
    {
        User user = userRepository.getUserByUsername(username);
        if( user !=  null)
        {
            return false;
        }
        else
        {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.passwordEncoder().encode(password));
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setVille(ville);
            newUser.setAdresse(adresse);
            newUser.setPays(pays);
            newUser.setCodePostal(codePostal);
            userRepository.save(newUser);
            return true;
        }

    }
    public boolean changePassword(String username, String password)
    {
        User user = userRepository.getUserByUsername(username);
        if(user != null)
        {
            userRepository.delete(user);
            user.setPassword(passwordEncoder.passwordEncoder().encode(password));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean changeMail(String username, String mail)
    {
        User user = userRepository.getUserByUsername(username);
        if(user != null)
        {
            userRepository.delete(user);
            user.setEmail(mail);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public String getUsernameFromToken(String token)
    {
        token = token.substring(7);
        return TokenUtil.extractUsername(token);
    }

    public boolean isUserFromUsername(String username)
    {
        return userRepository.findByUsername(username) != null;
    }

    public List<String> getAllUsername()
    {
        List<User> users = userRepository.findAll();
        List<String> usernames = new ArrayList<>();
        int i =0;
        for (User user:users) {
            usernames.add(user.getUsername());
            i++;
        }
        return usernames;
    }
}
