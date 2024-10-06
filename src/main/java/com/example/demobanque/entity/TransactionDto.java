package com.example.demobanque.entity;

import java.time.LocalDateTime;

public class TransactionDto {

    private Double montant;
    private LocalDateTime dateTransaction;
    private String type;
    private Long compte_id;

    // Getters and setters

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCompte_id() {
        return compte_id;
    }

    public void setCompte_id(Long compte_id) {
        this.compte_id = compte_id;
    }
}
