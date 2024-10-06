package com.example.demobanque.controller;

import com.example.demobanque.entity.Utilisateur;
import com.example.demobanque.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        return ResponseEntity.ok(utilisateur);
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) throws Utilisateur.EmailAlreadyExistsException {
        Utilisateur newUtilisateur = utilisateurService.saveUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUtilisateur);
    }

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> registerUtilisateur(@RequestBody Utilisateur utilisateur) throws Utilisateur.EmailAlreadyExistsException {
        Utilisateur newUtilisateur = utilisateurService.saveUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUtilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
