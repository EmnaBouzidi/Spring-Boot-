package com.example.demobanque.controller;

import com.example.demobanque.entity.Utilisateur;
import com.example.demobanque.payload.AuthRequest;
import com.example.demobanque.controller.AuthResponse;
import com.example.demobanque.repository.UtilisateurRepository;
import com.example.demobanque.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Utilisateur utilisateur = authService.authenticate(authRequest.getEmail(), authRequest.getPassword());
        if (utilisateur != null) {
            // Déterminez l'URL de redirection en fonction du rôle
            String redirectUrl = "/dashboard";
            if ("admin".equals(utilisateur.getRole())) {
                redirectUrl = "/admin-dashboard";
            } else if ("client".equals(utilisateur.getRole())) {
                redirectUrl = "/client-dashboard";
            }

            return ResponseEntity.ok(new AuthResponse("Login réussi!", true, utilisateur.getId(), utilisateur.getRole(), redirectUrl));
        } else {
            return ResponseEntity.badRequest().body(new AuthResponse("Échec de la connexion : email ou mot de passe incorrect", false, null, null, null));
        }
    }


    @PostMapping("/register")
    public String register(@RequestBody Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return "User registered successfully";
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Implémentez le code de déconnexion ici
        return ResponseEntity.ok("Logged out successfully");
    }


}
