package com.projetweb.reminder.service;

import com.projetweb.reminder.Sondage;
import com.projetweb.reminder.User;
import com.projetweb.reminder.repository.*;
import com.projetweb.reminder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SondageService {

    @Autowired
    SondageRepository sondageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public boolean enregistrer(Sondage sondage){
        if(sondageRepository.save(sondage)!=null)
        {
            return true;
        }
        return false;
    }

    public List<Sondage> getSondageRepository() {
        return sondageRepository.findAll();
    }

    public List<Sondage> getSondageByUsername(String username)
    {
        List<Sondage> sondages = sondageRepository.findAll();
        List<Sondage> sondagesList = new ArrayList<>();

        for (Sondage sondage:sondages) {
            String[] split = sondage.getParticipants().split(", ");
            if(Arrays.asList(split).contains(username))
                sondagesList.add(sondage);
        }
        return sondagesList;
    }

    public List<String> getLieux(Sondage sondage)
    {

        String[] split = sondage.getLieu().split(", ");

        return Arrays.asList(split);
    }

    public List<String> getDates(Sondage sondage)
    {
        String[] split = sondage.getDate().split(", ");

        return Arrays.asList(split);
    }
}
