package com.example.demobanque.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private CompteBancaire compteBancaire;

    private Double montant;

    private LocalDateTime dateTransaction;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public enum TypeTransaction {
        DEPOT,
        RETRAIT,
        TRANSFERT
    }
}
