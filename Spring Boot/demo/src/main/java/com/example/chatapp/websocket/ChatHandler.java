package com.example.chatapp.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Handle incoming messages from clients
        System.out.println("Received message: " + message.getPayload());
        try {
            session.sendMessage(new TextMessage("Message received: " + message.getPayload()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
