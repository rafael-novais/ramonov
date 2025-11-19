package com.ramonov.conf.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Broker simples em memória para teste (destinos começando com /topic)
        config.enableSimpleBroker("/topic");
        // prefixo para enviar mensagens que vão para @MessageMapping
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint socket que o client conecta (com fallback SockJS)
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}