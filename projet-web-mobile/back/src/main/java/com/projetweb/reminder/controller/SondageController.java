package com.projetweb.reminder.controller;

import com.projetweb.reminder.User;
import com.projetweb.reminder.service.UserService;
import com.projetweb.reminder.service.SondageService;
import com.projetweb.reminder.Sondage;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin(origins = "*")

public class SondageController {

    @Autowired
    SondageService sondageService;

    @Autowired
    UserService userService;


    @PostMapping("sondage/create")
    @ResponseBody
    public boolean createSondage(@RequestBody Sondage sondage, @RequestHeader("Authorization") Map<String, String> headers) {
        String username = userService.getUsernameFromToken(headers.get("authorization"));
        User user = userService.getUserByUsername(username);
        sondage.setCreator(user);
        return this.sondageService.enregistrer(sondage);
    }

    @GetMapping("sondage/get")
    @ResponseBody
    public List<Sondage> getSondage(@RequestHeader("Authorization") Map<String, String> headers) {
        return this.sondageService.getSondageRepository();
    }
}

