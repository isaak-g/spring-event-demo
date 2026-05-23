package com.example.demo.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationSocketHandler extends TextWebSocketHandler {

    // Key = Department (e.g., "PHARMACY", "ALL"), Value = List of open user application links
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<WebSocketSession>> departmentSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();
        String department = "ALL"; // Fallback default group

        if (query != null && query.contains("department=")) {
            department = query.split("department=")[1].toUpperCase();
        }

        departmentSessions.computeIfAbsent(department, k -> new CopyOnWriteArrayList<>()).add(session);
        System.out.println("📡 Running Swing Application connected to Notification channel for: " + department);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Remove dead application references to save system RAM resources
        departmentSessions.values().forEach(list -> list.remove(session));
    }

    public void broadcastToDepartment(String department, String jsonPayload) {
        TextMessage textMessage = new TextMessage(jsonPayload);

        // Target specific department list
        CopyOnWriteArrayList<WebSocketSession> sessions = departmentSessions.get(department.toUpperCase());
        if (sessions != null) {
            sessions.removeIf(session -> !session.isOpen()); // Clear dropped connections
            sessions.forEach(session -> {
                try { session.sendMessage(textMessage); } catch (Exception e) { e.printStackTrace(); }
            });
        }

        // Also broadcast to the general "ALL" channel if it's a generic system memo/maintenance alert
        if (department.equalsIgnoreCase("ALL")) {
            departmentSessions.values().forEach(list -> list.forEach(session -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(textMessage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        }
    }
}