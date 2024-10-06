package com.example.demobanque.service;

import com.example.demobanque.entity.DemandeCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditRequestService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private DemandeCreditService demandeCreditService;

    public void processCreditRequest(Long requestId, boolean approved) {
        String message = approved ? "Votre demande de crédit a été acceptée." : "Votre demande de crédit a été refusée.";
        Optional<DemandeCredit> demandeCreditOpt = demandeCreditService.getDemandeById(requestId);

        if (demandeCreditOpt.isPresent()) {
            String clientEmail = demandeCreditOpt.get().getEmail();
            notificationService.createNotification(message, approved ? "ACCEPTED" : "REJECTED", clientEmail);
        } else {
            throw new RuntimeException("Demande de crédit non trouvée avec l'ID: " + requestId);
        }
    }
}
