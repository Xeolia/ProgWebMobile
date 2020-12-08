package com.projetweb.reminder.service;

import com.projetweb.reminder.Sondage;
import com.projetweb.reminder.repository.*;
import com.projetweb.reminder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
    public class SondageService {

        @Autowired
        SondageRepository sondageRepository;
        @Autowired
        private JwtUtil jwtUtil;

        public boolean enregistrer(Sondage sondage){
            if(sondageRepository.save(sondage)!=null)
            {
                return true;
            }
            return false;
        }
        public String  getUsernameFromToken(String token){
            token=token.substring(7);
            return jwtUtil.extractUsername(token);
        }

        public List<Sondage> getSondageRepository(String token) {
            return sondageRepository.findAllByCreatorEquals(token);
        }
}
