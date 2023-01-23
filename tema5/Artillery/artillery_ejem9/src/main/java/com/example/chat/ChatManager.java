package com.example.chat;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;

@ServerEndpoint("/chat")
public class ChatManager {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ChatManager.class);

    private static final Map<String, User> room = new ConcurrentHashMap<>();
    private static AtomicLong id = new AtomicLong(0L);

    private User user;

    @OnOpen
    public void open(Session session) {
        this.user = new User(session, "user_"+id.incrementAndGet());
        room.put(user.getName(), user);
        this.user.send("{\"type\": \"system\", \"name\": \""+user.getName()+"\"}");
        logger.info("Nuevo usuario");
    }

    @OnMessage
    public void handleMessage(Session session, String message) throws IOException {
        logger.info(message + "from user: "+user.getName());
        room.values().forEach( _user -> _user.send(message) );
    }

    @OnClose
    public void close(Session session){

        logger.info("Sesión cerrada");
        room.remove(this.user.getName());

    }

    @OnError
    public void onError(Session session, Throwable thr) {
        logger.error("Cliente "+session.getId()+" desconectado");
    }

}
