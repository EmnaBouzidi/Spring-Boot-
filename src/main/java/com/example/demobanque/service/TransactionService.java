package com.example.demobanque.service;

import com.example.demobanque.entity.CompteBancaire;
import com.example.demobanque.entity.Transaction;
import com.example.demobanque.repository.CompteBancaireRepository;
import com.example.demobanque.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CompteBancaireRepository compteBancaireRepository; // Injecter le repository

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CompteBancaireRepository compteBancaireRepository) {
        this.transactionRepository = transactionRepository;
        this.compteBancaireRepository = compteBancaireRepository; // Initialiser le repository
    }

    // Récupérer toutes les transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Récupérer une transaction par son ID
    public Transaction getTransactionById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        return transactionOptional.orElse(null);
    }

    // Sauvegarder une nouvelle transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Supprimer une transaction par son ID
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // Méthode pour formater les dates (si nécessaire)
    public String formatDate(LocalDateTime dateTransaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return dateTransaction.format(formatter);
    }

    // Méthode pour récupérer un compte bancaire par ID
    public CompteBancaire getCompteBancaireById(Long id) {
        return compteBancaireRepository.findById(id).orElse(null); // Utiliser l'instance injectée
    }
}
