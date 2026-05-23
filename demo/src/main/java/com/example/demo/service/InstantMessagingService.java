package com.example.demo.service;

import com.example.demo.event.MessageSentEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class InstantMessagingService {

    private final ApplicationEventPublisher publisher;

    // Spring constructor injection
    public InstantMessagingService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendInstantMessage(String recipient, String content) {
        System.out.println("Processing IM to: " + recipient);

        // Fire the event! All registered listeners will instantly catch this.
        publisher.publishEvent(new MessageSentEvent(this, recipient, content));
    }
}