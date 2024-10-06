package com.example.demobanque.repository;

import com.example.demobanque.entity.DemandeCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour accéder aux données des demandes de crédit.
 */
@Repository
public interface DemandeCreditRepository extends JpaRepository<DemandeCredit, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}
