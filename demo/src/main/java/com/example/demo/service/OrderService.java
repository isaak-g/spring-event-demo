package com.example.demo.service;

import com.example.demo.event.OrderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ApplicationEventPublisher publisher;

    public OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void createOrder(String orderId) {
        System.out.println("Creating order: " + orderId);

        publisher.publishEvent(new OrderCreatedEvent(orderId));
    }
}