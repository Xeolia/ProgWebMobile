package com.projetweb.reminder.controller;

import com.projetweb.reminder.User;
import com.projetweb.reminder.models.AuthentificationRequest;
import com.projetweb.reminder.models.AuthentificationResponse;
import com.projetweb.reminder.service.MyUserDetailsService;
import com.projetweb.reminder.service.UserService;
import com.projetweb.reminder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil Tokenutil;
    @PostMapping("/user/authentification")
    public ResponseEntity<?> createAuthentificationToken(@RequestBody AuthentificationRequest authenticationrequest) throws Exception {
        try {
            authenticationManager.authenticate
                    (
                            new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword())
                    );
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Nom d'utilisateur ou mot de passe incorrect", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationrequest.getUsername());
        final String token = Tokenutil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthentificationResponse(token));
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUserByUsername(@RequestHeader("Authorization") Map<String, String> headers)
    {
        String username = userService.getUsernameFromToken(headers.get("authorization"));
        User user =  userService.getUserByUsername(username);
        return user;
    }



    @PostMapping("/user/registration")
    @ResponseBody
    public boolean createUser(@RequestBody User user)
    {
        if (userService.isUserFromUsername(user.getUsername())){
            return false;
        }
        return userService.createUser(user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getAdresse(),user.getVille(),user.getPays(),user.getCodePostal());
    }

    @PostMapping("/user/password")
    @ResponseBody
    public boolean changePassword(@RequestBody Map<String, String> requestsBody,@RequestHeader("Authorization") Map<String, String> headers)
    {
        String password = requestsBody.get("password");
        String username = userService.getUsernameFromToken(headers.get("authorization"));
        return userService.changePassword(username,password);
    }

    @PostMapping("/user/mail")
    @ResponseBody
    public boolean changeMail(@RequestBody Map<String, String> requestsBody,@RequestHeader("Authorization") Map<String, String> headers)
    {
        String email = requestsBody.get("email");
        String username = userService.getUsernameFromToken(headers.get("authorization"));

        return userService.changeMail(username,email);
    }


    @GetMapping("/user/loadusernames")
    @ResponseBody
    public List<String> loadUsername(@RequestHeader("Authorization") Map<String, String> headers)
    {
        return userService.getAllUsername();
    }
}
