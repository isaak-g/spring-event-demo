package com.example.demo.config;

import com.example.demo.handler.InstantMessagingSocketHandler;
import com.example.demo.handler.NotificationSocketHandler; // 1. Added missing import
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final InstantMessagingSocketHandler imHandler;
    private final NotificationSocketHandler notificationHandler; //  the second handler variable

    // constructor to inject BOTH background real-time handlers
    public WebSocketConfig(InstantMessagingSocketHandler imHandler, NotificationSocketHandler notificationHandler) {
        this.imHandler = imHandler;
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Individual User Chat Channel (Passes down imHandler)
        registry.addHandler(imHandler, "/ws/messages").setAllowedOrigins("*");

        // UNIFIED SYSTEM ALERTS & BROADCAST CHANNEL (Passes down notificationHandler)
        registry.addHandler(notificationHandler, "/ws/notifications").setAllowedOrigins("*");
    }
}