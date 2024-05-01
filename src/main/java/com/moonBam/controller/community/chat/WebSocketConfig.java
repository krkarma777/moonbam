package com.moonBam.controller.community.chat;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
		// http://localhost:8090/acorn/chat-socket
		registry.addEndpoint("/chat-socket").setAllowedOrigins("http://localhost:9092").withSockJS();
    }
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/acorn");
		registry.enableSimpleBroker("/topic/");
        
    }
}