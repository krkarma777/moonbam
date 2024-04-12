package com.moonBam.controller.community;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

@Service
@ServerEndpoint(value="/chatt")
public class WebSocketChatt {
	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void onMessage(String msg, Session session) throws Exception{
		
	}
	
	@OnOpen
	public void onOpen(Session s) {

	}
	
	@OnClose
	public void onClose(Session s) {

	}
}