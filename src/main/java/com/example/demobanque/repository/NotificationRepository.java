package com.example.demobanque.repository;

import com.example.demobanque.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUtilisateurId(Long utilisateurId);
}
