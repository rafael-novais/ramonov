package com.ramonov.controllers;

import com.ramonov.dto.messages.Greeting;
import com.ramonov.dto.messages.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketHealthController {
    // Recebe mensagens enviadas para /app/hello
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting handleHello(HelloMessage message) throws Exception {
        // simula processamento leve
        String body = "Hello, " + (message.getName() == null ? "world" : message.getName()) + "!";
        return new Greeting(body);
    }
}
