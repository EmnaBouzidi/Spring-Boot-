package com.example.demobanque.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String password;

    private String role;

    private Double salaire;

    private LocalDate dateDeNaissance;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public static class EmailAlreadyExistsException extends Exception {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }

    // Méthode de validation
    public void validate() throws IllegalArgumentException {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }

        if (prenom == null || prenom.isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être vide");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("L'email ne peut pas être vide");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide");
        }

        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$")) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins une lettre, un chiffre et un caractère spécial (@#$!%*?&), et faire au moins 8 caractères de long.");
        }
    }

}
