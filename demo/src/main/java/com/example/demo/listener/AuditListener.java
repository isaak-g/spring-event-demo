package com.example.demo.listener;

import com.example.demo.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditListener {

    @EventListener
    public void auditUser(UserCreatedEvent event) {
        System.out.println("AUDIT: User " + event.username() + " recorded in audit log");
    }
}