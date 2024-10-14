package com.future.my.common.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 처음 접속시
		registry.addEndpoint("/endpoint").withSockJS();
		
	}

	
	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// subscribe 시작하는 메세지 라우팅
		registry.enableSimpleBroker("/subscribe");
		// /app/hello/+ roomNo 형태로 요청이 오면 -> /hello/{roomNo}으로 전달
		registry.setApplicationDestinationPrefixes("/app");
		
	}
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String userId = sha.getFirstNativeHeader("userId");
		String roomNo = sha.getFirstNativeHeader("roomNo");
		
		Map<String, Object>message = new HashMap<>();
		message.put("type", "notification");
		message.put("message",userId +"님이 입장하셨습니다.");
		messagingTemplate.convertAndSend("subscribe/chat/"+roomNo, message);
	}
	

}
