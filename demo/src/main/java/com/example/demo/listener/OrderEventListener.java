package com.example.demo.listener;

import com.example.demo.event.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("ORDER EVENT RECEIVED: " + event.orderId());
    }
}