package com.projetweb.reminder.controller;

import com.projetweb.reminder.User;
import com.projetweb.reminder.service.UserService;
import com.projetweb.reminder.service.SondageService;
import com.projetweb.reminder.Sondage;
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


        @PostMapping("sondage/create")
        @ResponseBody
        public boolean createSondage(@RequestBody Sondage sondage, @RequestHeader("Authorization") Map<String, String> headers) {
            String token = sondageService.getUsernameFromToken(headers.get("authorization"));
            sondage.setCreator(token);
            return this.sondageService.enregistrer(sondage);
        }

        @GetMapping("sondage/get")
        @ResponseBody
        public List<Sondage> getSondage(@RequestHeader("Authorization") Map<String, String> headers) {
            String token = sondageService.getUsernameFromToken(headers.get("authorization"));
            return sondageService.getSondageRepository(token);
        }
}

