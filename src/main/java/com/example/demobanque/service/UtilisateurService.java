package com.example.demobanque.service;

import com.example.demobanque.entity.Utilisateur;
import com.example.demobanque.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        return utilisateurOptional.orElse(null);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws Utilisateur.EmailAlreadyExistsException {
        // Valider l'utilisateur avant de l'enregistrer
        utilisateur.validate();

        // Vérifiez si l'email est déjà utilisé
        Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (existingUser.isPresent()) {
            throw new Utilisateur.EmailAlreadyExistsException("Adresse email déjà utilisée : " + utilisateur.getEmail());
        }

        // Si l'email n'est pas déjà utilisé, enregistrez l'utilisateur
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public void updateUtilisateur(Utilisateur utilisateur) {
        // Valider l'utilisateur avant de le mettre à jour
        utilisateur.validate();
        // Logique pour mettre à jour l'utilisateur en base de données
        utilisateurRepository.save(utilisateur);
    }
}
