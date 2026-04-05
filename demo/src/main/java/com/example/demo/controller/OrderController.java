package com.example.demo.controller;

import com.example.demo.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String home() {
        return "API is running! Use POST /orders to create a orders.";
    }

    @PostMapping
    public String createOrder(@RequestParam String orderId) {
        orderService.createOrder(orderId);
        return "Order created!";
    }
}