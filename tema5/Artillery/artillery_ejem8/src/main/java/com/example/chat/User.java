package com.example.chat;

import java.io.IOException;

import jakarta.websocket.Session;

public class User {
	private String name;
	private final Session session;
	
	public User(Session session, String name) {
		this.session = session;
		this.name = name;
	}
	
	public synchronized void send(String message){
		try {
				this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public String getName(){
		return this.name;
	}

}
