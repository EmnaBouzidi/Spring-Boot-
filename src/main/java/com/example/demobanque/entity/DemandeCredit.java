package com.example.demobanque.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "demandes_credit")
public class DemandeCredit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String cin;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String numeroCompte;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private String butCredit;

    @Column(nullable = false)
    private Integer dureeMois;

    // Getters and Setters
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getButCredit() {
        return butCredit;
    }

    public void setButCredit(String butCredit) {
        this.butCredit = butCredit;
    }

    public Integer getDureeMois() {
        return dureeMois;
    }

    public void setDureeMois(Integer dureeMois) {
        this.dureeMois = dureeMois;
    }

    @Override
    public String toString() {
        return "DemandeCredit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cin='" + cin + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", adresse='" + adresse + '\'' +
                ", numeroCompte='" + numeroCompte + '\'' +
                ", montant=" + montant +
                ", butCredit='" + butCredit + '\'' +
                ", dureeMois=" + dureeMois +
                '}';
    }
}
