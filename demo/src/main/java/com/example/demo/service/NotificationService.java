package com.example.demo.service;

import com.example.demo.event.NotificationEvent;
import com.example.demo.event.NotificationEvent.NotificationType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final ApplicationEventPublisher eventPublisher;
    public final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NotificationService.class.getName());

    public NotificationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Unified core method to publish events to your Spring application ecosystem.
     */
    public void createNotification(String title, String message, String department, NotificationType type) {
        logger.info("Publishing Notification Event -> Title: " + title + " | Target: " + department);
        NotificationEvent event = new NotificationEvent(this, title, message, department, type);
        eventPublisher.publishEvent(event);
    }

    /**
     * Handles manual notifications sent to a single specific user (via /api/notifications/notification)
     */
    public void sendUserNotification(String username, String jsonPayload) {
        logger.info("Processing manual individual user notification...");

        // REUSE: Calls the core method, satisfying IntelliJ's usage scanner
        createNotification("Direct Message", jsonPayload, username, NotificationType.ACTION_REQUIRED);
    }

    /**
     * Handles manual broadcast notifications sent to an entire department (via /api/notifications/broadcast)
     */
    public void sendDepartmentNotification(String department, String jsonPayload) {
        logger.info("Processing manual department wide broadcast...");

        // REUSE: Calls the core method, satisfying IntelliJ's usage scanner
        createNotification("System Alert", jsonPayload, department, NotificationType.SYSTEM_MEMO);
    }
}