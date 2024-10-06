package com.example.demobanque.service;

import com.example.demobanque.entity.CompteBancaire;
import com.example.demobanque.repository.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteBancaireService {

    private final CompteBancaireRepository compteBancaireRepository;

    @Autowired
    public CompteBancaireService(CompteBancaireRepository compteBancaireRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
    }

    public List<CompteBancaire> getAllComptesBancaires() {
        return compteBancaireRepository.findAll();
    }

    // Changer cette méthode pour qu'elle ne soit pas statique
    public CompteBancaire getCompteBancaireById(Long id) {
        Optional<CompteBancaire> compteBancaireOptional = compteBancaireRepository.findById(id);
        return compteBancaireOptional.orElse(null);
    }

    public CompteBancaire saveCompteBancaire(CompteBancaire compteBancaire) {
        return compteBancaireRepository.save(compteBancaire);
    }

    public void deleteCompteBancaire(Long id) {
        compteBancaireRepository.deleteById(id);
    }

    public List<CompteBancaire> getComptesByUtilisateurId(Long utilisateurId) {
        return compteBancaireRepository.findByUtilisateurId(utilisateurId);
    }
}
