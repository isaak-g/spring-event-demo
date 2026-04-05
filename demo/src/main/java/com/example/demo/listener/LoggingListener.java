package com.example.demo.listener;

import com.example.demo.event.OrderCreatedEvent;
import com.example.demo.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoggingListener {

    @EventListener
    public void logUserCreation(UserCreatedEvent event) {
        System.out.println("LOG: User created -> " + event.username());
    }

    @EventListener
    public void logOrder(OrderCreatedEvent event) {
        System.out.println("LOG: Order created -> " + event.orderId());
    }
}
