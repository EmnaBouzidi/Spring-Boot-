package com.example.demobanque.repository;

import com.example.demobanque.entity.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
    List<CompteBancaire> findByUtilisateurId(Long utilisateurId); // Méthode pour trouver des comptes par utilisateur
}
