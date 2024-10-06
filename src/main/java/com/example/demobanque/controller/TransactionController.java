package com.example.demobanque.controller;

import com.example.demobanque.entity.Transaction;
import com.example.demobanque.entity.CompteBancaire;
import com.example.demobanque.entity.TransactionDto; // Ensure this import is correct
import com.example.demobanque.repository.CompteBancaireRepository;
import com.example.demobanque.repository.TransactionRepository;
import com.example.demobanque.service.TransactionService;
import com.example.demobanque.service.CompteBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CompteBancaireService compteBancaireService;
    private final CompteBancaireRepository compteBancaireRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(
            TransactionService transactionService,
            CompteBancaireService compteBancaireService,
            CompteBancaireRepository compteBancaireRepository,
            TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.compteBancaireService = compteBancaireService;
        this.compteBancaireRepository = compteBancaireRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) {
        List<String> errors = validateTransactionDto(transactionDto);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Validate the compte_id
            Optional<CompteBancaire> optionalCompte = compteBancaireRepository.findById(transactionDto.getCompte_id());
            if (!optionalCompte.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No resource with given identifier found");
            }

            // Convert String to enum for type
            Transaction.TypeTransaction typeTransaction;
            try {
                typeTransaction = Transaction.TypeTransaction.valueOf(transactionDto.getType());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid transaction type");
            }

            // Create and set the transaction
            Transaction transaction = new Transaction();
            transaction.setMontant(transactionDto.getMontant());
            transaction.setDateTransaction(transactionDto.getDateTransaction());
            transaction.setType(typeTransaction);
            transaction.setCompteBancaire(optionalCompte.get());

            // Save the transaction
            Transaction savedTransaction = transactionRepository.save(transaction);
            return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception details (optional)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the transaction");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private List<String> validateTransactionDto(TransactionDto transactionDto) {
        List<String> errors = new ArrayList<>();

        // Validate montant
        if (transactionDto.getMontant() == null || transactionDto.getMontant() <= 0) {
            errors.add("Montant invalide");
        }

        // Validate dateTransaction
        if (transactionDto.getDateTransaction() == null) {
            errors.add("Date de transaction manquante");
        }

        // Validate type
        if (transactionDto.getType() == null || transactionDto.getType().isEmpty()) {
            errors.add("Type de transaction manquant");
        } else {
            // Validate that type is a valid enum value
            try {
                Transaction.TypeTransaction.valueOf(transactionDto.getType());
            } catch (IllegalArgumentException e) {
                errors.add("Type de transaction invalide");
            }
        }

        // Validate compte_id
        if (transactionDto.getCompte_id() == null) {
            errors.add("Compte bancaire manquant");
        }

        return errors;
    }
}
