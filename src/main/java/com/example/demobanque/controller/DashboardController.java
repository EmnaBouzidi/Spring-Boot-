package com.example.demobanque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    // Méthode pour afficher les vues des tableaux de bord
    @GetMapping("/dashboard/view")
    public String dashboardView(@RequestParam String role) {
        switch (role) {
            case "admin":
                return "admin-dashboard"; // Nom du template pour le tableau de bord admin
            case "client":
                return "dashboard"; // Nom du template pour le tableau de bord client
            default:
                return "redirect:/login"; // Redirection vers la page de connexion par défaut
        }
    }

    // Méthode pour l'API qui retourne des données JSON
    @GetMapping("/dashboard/data")
    public ResponseEntity<?> getDashboardData(@RequestParam String role) {
        // Implémentez votre logique ici
        return ResponseEntity.ok("Dashboard content for role: " + role);
    }
}
