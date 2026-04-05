package com.example.demo.listener;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Async
    @EventListener
    public void sendWelcomeEmail(UserCreatedEvent event) {
        System.out.println("EMAIL: Welcome email sent to " + event.username());
    }

    @EventListener
    public void sendOrderCreated(OrderCreatedEvent event) {
        System.out.println("EMAIL: Order created -> " + event.orderId());
    }
}