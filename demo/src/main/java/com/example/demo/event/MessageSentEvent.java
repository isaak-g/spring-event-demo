package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class MessageSentEvent extends ApplicationEvent {
    private final String recipient;
    private final String content;

    public MessageSentEvent(Object source, String recipient, String content) {
        super(source);
        this.recipient = recipient;
        this.content = content;
    }

    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
}