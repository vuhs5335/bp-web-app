package com.hersa.app;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/wsServer")
public class WsServer {

	@OnOpen
	public void OnOpen(Session session) {
		System.out.println(session.toString());
	}
	
	@OnMessage
	public void onMessage(Session session, byte[] img) {
		ByteBuffer buffer = ByteBuffer.wrap(img);
		
		try {
			session.getBasicRemote().sendBinary(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
