package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {

    public enum NotificationType {
        STOCK_ALERT,
        SYSTEM_MEMO,
        MAINTENANCE,
        ACTION_REQUIRED
    }

    private final String title;
    private final String message;
    private final String targetDepartment; // e.g., "PHARMACY", "ALL", "ADMIN"
    private final NotificationType type;

    public NotificationEvent(Object source, String title, String message, String targetDepartment, NotificationType type) {
        super(source);
        this.title = title;
        this.message = message;
        this.targetDepartment = targetDepartment;
        this.type = type;
    }

    // Getters
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getTargetDepartment() { return targetDepartment; }
    public NotificationType getType() { return type; }
}