package com.moonBam.controller.community.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
		// http://localhost:8090/acorn/chat-socket
		// 강사님께 질문 만약 주소가 
//		registry.addEndpoint("/chat-socket").setAllowedOrigins("http://localhost:9092").withSockJS();
		registry.addEndpoint("/chat-socket").setAllowedOrigins("http://localhost:9092").withSockJS();
    }
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
		//long[] heartbeart = {30000l, 30000l}; //30000ms마다 연결 확인하기
    	registry.enableSimpleBroker("/topic/");
        registry.setApplicationDestinationPrefixes("/acorn");
    }
	
	/*
	 * // heart beat: 연결 확인 지속적으로 하는 것
	 * 
	 * @Bean public TaskScheduler heartBeatScheduler() { return new
	 * ThreadPoolTaskScheduler(); }
	 */
	
}