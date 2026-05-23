package com.example.demo.listener;

import com.example.demo.event.MessageSentEvent;
import com.example.demo.handler.InstantMessagingSocketHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InstantMessagingListener {

    private final InstantMessagingSocketHandler socketHandler;

    public InstantMessagingListener(InstantMessagingSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @EventListener
    public void handleMessageSent(MessageSentEvent event) {
        System.out.println("IM EVENT LOGGED -> Recipient: " + event.getRecipient());

        // Format message data cleanly into standard JSON string
        String jsonPayload = String.format("{\"content\":\"%s\"}", event.getContent());

        // Push it down the pipe directly to the user's desktop client!
        socketHandler.sendMessageToUser(event.getRecipient(), jsonPayload);
    }
}