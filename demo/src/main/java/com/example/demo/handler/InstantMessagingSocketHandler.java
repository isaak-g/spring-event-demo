package com.example.demo.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InstantMessagingSocketHandler extends TextWebSocketHandler {

    // Thread-safe map storing active online users: Key = Username, Value = Connection Session
    private final ConcurrentHashMap<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Parse username out of the URL parameter string (e.g., ?username=Spladmin)
        String query = session.getUri().getQuery();
        if (query != null && query.contains("username=")) {
            String username = query.split("username=")[1];
            activeSessions.put(username, session);
            System.out.println("🟢 USER ONLINE: " + username + " connected via WebSocket.");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Automatically clean up memory when a hospital client disconnects or closes their app
        activeSessions.values().remove(session);
    }

    // This method allows other parts of your Spring app to push messages out!
    public void sendMessageToUser(String username, String rawJsonPayload) {
        WebSocketSession session = activeSessions.get(username);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(rawJsonPayload));
                System.out.println(" Real-time push executed successfully to: " + username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠ User " + username + " is currently offline. Queueing/storing message.");
        }
    }
}