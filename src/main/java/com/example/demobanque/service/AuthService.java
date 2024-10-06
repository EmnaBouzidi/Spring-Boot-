package com.example.demobanque.service;

import com.example.demobanque.entity.Utilisateur;
import com.example.demobanque.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur authenticate(String email, String password) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmail(email);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            if (utilisateur.getPassword().equals(password)) {
                return utilisateur; // Authentification réussie
            }
        }
        return null; // Authentification échouée
    }
}
