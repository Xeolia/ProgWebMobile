package com.projetweb.reminder.controller;

import com.projetweb.reminder.User;
import com.projetweb.reminder.Vote;
import com.projetweb.reminder.service.UserService;
import com.projetweb.reminder.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    UserService userService;


    @PostMapping("vote/create")
    @ResponseBody
    public boolean createVote(@RequestBody Vote vote, @RequestHeader("Authorization") Map<String, String> headers) {
        String username = userService.getUsernameFromToken(headers.get("authorization"));
        User user = userService.getUserByUsername(username);
        vote.setVotant(user);
        return this.voteService.enregistrer(vote);
    }
}
