package com.example.demo.listener;

import com.example.demo.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        System.out.println("EVENT RECEIVED: " + event.username());
    }
}