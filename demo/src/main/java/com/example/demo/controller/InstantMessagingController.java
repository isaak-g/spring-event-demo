package com.example.demo.controller;

import com.example.demo.service.InstantMessagingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class InstantMessagingController {

    private final InstantMessagingService imService;

    public InstantMessagingController(InstantMessagingService imService) {
        this.imService = imService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam String recipient, @RequestParam String content) {
        imService.sendInstantMessage(recipient, content);
    }
}