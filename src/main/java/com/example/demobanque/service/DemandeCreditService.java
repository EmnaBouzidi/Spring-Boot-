package com.example.demobanque.service;

import com.example.demobanque.entity.DemandeCredit;
import com.example.demobanque.repository.DemandeCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeCreditService {

    @Autowired
    private DemandeCreditRepository demandeCreditRepository;

    @Autowired
    private NotificationService notificationService; // Ajout du service NotificationService

    /**
     * Récupère toutes les demandes de crédit.
     * @return Liste de toutes les demandes de crédit.
     */
    public List<DemandeCredit> getAllDemandes() {
        return demandeCreditRepository.findAll();
    }

    /**
     * Récupère une demande de crédit par son ID.
     * @param id ID de la demande de crédit.
     * @return Objet DemandeCredit s'il est trouvé, sinon Optional.empty().
     */
    public Optional<DemandeCredit> getDemandeById(Long id) {
        return demandeCreditRepository.findById(id);
    }

    /**
     * Crée ou met à jour une demande de crédit.
     * @param demandeCredit Objet DemandeCredit à sauvegarder.
     * @return DemandeCredit sauvegardée.
     */
    public DemandeCredit saveDemande(DemandeCredit demandeCredit) {
        return demandeCreditRepository.save(demandeCredit);
    }

    /**
     * Met à jour une demande de crédit.
     * @param id ID de la demande de crédit à mettre à jour.
     * @param demandeCredit Objet DemandeCredit contenant les nouvelles données.
     * @return DemandeCredit mise à jour.
     */
    public DemandeCredit update(Long id, DemandeCredit demandeCredit) {
        Optional<DemandeCredit> existingDemande = demandeCreditRepository.findById(id);
        if (existingDemande.isPresent()) {
            DemandeCredit updatedDemande = existingDemande.get();
            updatedDemande.setNom(demandeCredit.getNom());
            updatedDemande.setPrenom(demandeCredit.getPrenom());
            updatedDemande.setEmail(demandeCredit.getEmail());
            updatedDemande.setTelephone(demandeCredit.getTelephone());
            updatedDemande.setAdresse(demandeCredit.getAdresse());
            // Mettez à jour les autres champs nécessaires
            return demandeCreditRepository.save(updatedDemande);
        } else {
            throw new RuntimeException("Demande de crédit non trouvée avec l'ID: " + id);
        }
    }

    /**
     * Supprime une demande de crédit par son ID.
     * @param id ID de la demande de crédit à supprimer.
     */
    public void deleteDemande(Long id) {
        demandeCreditRepository.deleteById(id);
    }

    /**
     * Traite une demande de crédit, crée une notification pour le client selon le statut.
     * @param requestId ID de la demande de crédit.
     * @param approved Statut de la demande de crédit (approuvée ou refusée).
     */
    public void processCreditRequest(Long requestId, boolean approved) {
        String message = approved ? "Votre demande de crédit a été acceptée." : "Votre demande de crédit a été refusée.";
        Optional<DemandeCredit> demandeCreditOpt = getDemandeById(requestId);

        if (demandeCreditOpt.isPresent()) {
            String clientEmail = demandeCreditOpt.get().getEmail();
            notificationService.createNotification(message, approved ? "ACCEPTED" : "REJECTED", clientEmail);
        } else {
            throw new RuntimeException("Demande de crédit non trouvée avec l'ID: " + requestId);
        }
    }
}
