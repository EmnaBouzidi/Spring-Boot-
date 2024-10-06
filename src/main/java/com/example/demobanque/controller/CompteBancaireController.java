package com.example.demobanque.controller;

import com.example.demobanque.entity.CompteBancaire;
import com.example.demobanque.service.CompteBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptesbancaires")
@CrossOrigin(origins = "http://localhost:4200")  // Autorise les requêtes venant de http://localhost:4200
public class CompteBancaireController {

    private final CompteBancaireService compteBancaireService;

    @Autowired
    public CompteBancaireController(CompteBancaireService compteBancaireService) {
        this.compteBancaireService = compteBancaireService;
    }

    @GetMapping
    public List<CompteBancaire> getAllComptesBancaires() {
        return compteBancaireService.getAllComptesBancaires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteBancaire> getCompteBancaireById(@PathVariable Long id) {
        CompteBancaire compteBancaire = compteBancaireService.getCompteBancaireById(id);
        if (compteBancaire != null) {
            return ResponseEntity.ok(compteBancaire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CompteBancaire> createCompteBancaire(@RequestBody CompteBancaire compteBancaire) {
        CompteBancaire newCompteBancaire = compteBancaireService.saveCompteBancaire(compteBancaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompteBancaire);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompteBancaire(@PathVariable Long id) {
        compteBancaireService.deleteCompteBancaire(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<CompteBancaire>> getComptesByUtilisateurId(@PathVariable Long id) {
        List<CompteBancaire> comptes = compteBancaireService.getComptesByUtilisateurId(id);
        return ResponseEntity.ok(comptes);
    }
}
