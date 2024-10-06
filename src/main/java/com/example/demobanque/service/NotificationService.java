package com.example.demobanque.service;

import com.example.demobanque.entity.Notification;
import com.example.demobanque.entity.Utilisateur;
import com.example.demobanque.repository.NotificationRepository;
import com.example.demobanque.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;  // Correctement injecté

    public Notification createNotification(String message, String type, String clientEmail) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(clientEmail)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));

        Notification notification = new Notification();
        notification.setUtilisateur(utilisateur);
        notification.setMessage(message);
        notification.setType(type);
        notification.setDate(LocalDateTime.now());
        notification.setIsRead(false);

        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUtilisateurId(Long utilisateurId) {
        return notificationRepository.findByUtilisateurId(utilisateurId);
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
}
