package com.example.demobanque.controller;

import com.example.demobanque.entity.DemandeCredit;
import com.example.demobanque.service.DemandeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/demandes-credit")
@CrossOrigin(origins = "http://localhost:4200") // Configuration CORS pour autoriser les requêtes depuis http://localhost:4200
public class DemandeCreditController {

    @Autowired
    private DemandeCreditService demandeCreditService;

    @GetMapping
    public ResponseEntity<List<DemandeCredit>> getAllDemandes() {
        List<DemandeCredit> demandes = demandeCreditService.getAllDemandes();
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeCredit> getDemandeById(@PathVariable Long id) {
        Optional<DemandeCredit> demande = demandeCreditService.getDemandeById(id);
        return demande.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DemandeCredit> createDemande(@RequestBody DemandeCredit demande) {
        System.out.println("Données reçues: " + demande);
        DemandeCredit newDemande = demandeCreditService.saveDemande(demande);
        return new ResponseEntity<>(newDemande, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeCredit> updateDemande(@PathVariable Long id, @RequestBody DemandeCredit demande) {
        System.out.println("Données reçues pour mise à jour: " + demande);
        DemandeCredit updatedDemande = demandeCreditService.update(id, demande);
        return new ResponseEntity<>(updatedDemande, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        demandeCreditService.deleteDemande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
