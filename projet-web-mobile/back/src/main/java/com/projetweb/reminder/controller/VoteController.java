package com.projetweb.reminder.controller;

import com.projetweb.reminder.Sondage;
import com.projetweb.reminder.User;
import com.projetweb.reminder.Vote;
import com.projetweb.reminder.repository.SondageRepository;
import com.projetweb.reminder.service.SondageService;
import com.projetweb.reminder.service.UserService;
import com.projetweb.reminder.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    UserService userService;

    @Autowired
    SondageRepository sondageRepository;


    @PostMapping("vote/create")
    @ResponseBody
    public boolean createVote(@RequestBody Map<String, String> body, @RequestHeader("Authorization") Map<String, String> headers) {
        Vote vote = new Vote();
        Sondage sondage;
        int id= parseInt(body.get("reference"));
        sondage = sondageRepository.getSondageById(id);

        vote.setReference(sondage);
        vote.setChoix(body.get("choix"));
        vote.setType(body.get("type"));
        String username = userService.getUsernameFromToken(headers.get("authorization"));
        User user = userService.getUserByUsername(username);
        vote.setVotant(user);
        return this.voteService.enregistrer(vote);
    }
}
