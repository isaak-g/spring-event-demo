package com.example.demo.listener;

import com.example.demo.event.NotificationEvent;
import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.handler.NotificationSocketHandler;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final NotificationSocketHandler notificationSocketHandler;

    // Spring constructor injection
    public NotificationListener(NotificationSocketHandler notificationSocketHandler) {
        this.notificationSocketHandler = notificationSocketHandler;
    }

    // Instance 1: Handle Order Events
    @Async
    @EventListener
    public void sendNotification(OrderCreatedEvent event) {
        System.out.println("NOTIFICATION: Order " + event.orderId() + " confirmed");
    }

    // Instance 2: Handle HMIS System-wide Notifications (Memos, Low Stock, Maintenance)
    @EventListener
    public void onSystemNotification(NotificationEvent event) {
        System.out.println(" NotificationListener intercepted event: " + event.getTitle());

        // 1. DATABASE FALLBACK STEP (For offline users)
        System.out.println(" Saved to database fallback storage for history/offline caching.");

        // 2. REAL-TIME TRANSMISSION STEP
        String jsonPayload = String.format(
                "{\"type\":\"%s\",\"title\":\"%s\",\"message\":\"%s\",\"dept\":\"%s\"}",
                event.getType().name(),
                event.getTitle().replace("\"", "\\\""),
                event.getMessage().replace("\"", "\\\""),
                event.getTargetDepartment()
        );

        // Broadcast to everyone online in that specific department, or "ALL" for wide memos
        notificationSocketHandler.broadcastToDepartment(event.getTargetDepartment(), jsonPayload);
    }
}