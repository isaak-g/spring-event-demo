package com.example.demo.controller;

import com.example.demo.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // Injecting the notification architectural layer cleanly
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Endpoint to manually broadcast system notifications to a target department.
     * This lights up the floating red badge on the Swing UI toolbar instantly.
     */
    @PostMapping("/notification")
    public void usersNotification(
            @RequestParam String username,
            @RequestParam String title,
            @RequestParam String message){
        // Wrap the payload cleanly into JSON formatting matching what your Swing app expects
        String jsonPayload = String.format("{\"title\":\"%s\",\"message\":\"%s\"}", title, message);

        // Hand off to the service layer to handle the active WebSocket transmission
        notificationService.sendUserNotification(username, jsonPayload);
    }
    @PostMapping("/broadcast")
    public void broadcastSystemAlert(
            @RequestParam String department,
            @RequestParam String title,
            @RequestParam String message) {

        // Wrap the payload cleanly into JSON formatting matching what your Swing app expects
        String jsonPayload = String.format("{\"title\":\"%s\",\"message\":\"%s\"}", title, message);

        // Hand off to the service layer to handle the active WebSocket transmission
        notificationService.sendDepartmentNotification(department, jsonPayload);
    }
}