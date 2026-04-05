package com.example.demo.listener;

import com.example.demo.event.OrderCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @EventListener
    public void processPayment(OrderCreatedEvent event) {
        System.out.println("PAYMENT: Processing payment for order " + event.orderId());
    }
}