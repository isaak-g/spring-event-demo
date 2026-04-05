package com.example.demo.listener;

import com.example.demo.event.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Async
    @EventListener
    public void sendNotification(OrderCreatedEvent event) {
        System.out.println("NOTIFICATION: Order " + event.orderId() + " confirmed");
    }
}