package com.projetweb.reminder.controller;

import com.projetweb.reminder.Sondage;
import com.projetweb.reminder.User;
import com.projetweb.reminder.Vote;
import com.projetweb.reminder.repository.SondageRepository;
import com.projetweb.reminder.repository.UserRepository;
import com.projetweb.reminder.repository.VoteRepository;
import com.projetweb.reminder.service.SondageService;
import com.projetweb.reminder.service.UserService;
import com.projetweb.reminder.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @Autowired
    VoteRepository voteRepository;


    @PostMapping("vote/create")
    @ResponseBody
    public boolean createVote(@RequestBody Map<String, String> body, @RequestHeader("Authorization") Map<String, String> headers) {

        List<Vote> votes = voteRepository.findAll();
        int id= parseInt(body.get("reference"));
        String username = userService.getUsernameFromToken(headers.get("authorization"));
        User user = userService.getUserByUsername(username);
        for (Vote vote:votes) {
            if(vote.getReference() == id && vote.getVotant() == user.getId() && vote.getType().equals(body.get("type"))){
                return false;
            }
        }


        Vote vote = new Vote();

        vote.setReference(id);
        vote.setChoix(body.get("choix"));
        vote.setType(body.get("type"));

        vote.setVotant(user.getId());
        return this.voteService.enregistrer(vote);
    }

    @PostMapping("vote/count")
    @ResponseBody
    public int countVote(@RequestBody Map<String, String> body, @RequestHeader("Authorization") Map<String, String> headers) {

        int id= parseInt(body.get("reference"));
        body.get("choix");
        body.get("type");
        List<Vote> votes = voteRepository.findByTypeAndChoixAndReference(body.get("type"),body.get("choix"), id);

        return votes.size();
    }
}
