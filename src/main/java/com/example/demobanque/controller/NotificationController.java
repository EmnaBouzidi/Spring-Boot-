package com.example.demobanque.controller;

import com.example.demobanque.entity.Notification;
import com.example.demobanque.service.NotificationService;
// Import correct
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationRequest request) {
        Notification notification = notificationService.createNotification(
                request.getMessage(),
                request.getType(),
                request.getClientEmail()
        );
        return ResponseEntity.ok(notification);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotificationsByUtilisateurId(
            @RequestParam("utilisateurId") Long utilisateurId) {
        List<Notification> notifications = notificationService.getNotificationsByUtilisateurId(utilisateurId);
        if (notifications == null || notifications.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/{notificationId}/mark-as-read")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok().build();
    }
}
