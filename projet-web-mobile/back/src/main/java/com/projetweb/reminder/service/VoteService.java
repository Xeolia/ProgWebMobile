package com.projetweb.reminder.service;

import com.projetweb.reminder.Vote;
import com.projetweb.reminder.repository.VoteRepository;
import com.projetweb.reminder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public boolean enregistrer(Vote vote){
        if(voteRepository.save(vote)!=null)
        {
            return true;
        }
        return false;
    }

    public List<Vote> getVoteRepository() {
        return voteRepository.findAll();
    }
}
