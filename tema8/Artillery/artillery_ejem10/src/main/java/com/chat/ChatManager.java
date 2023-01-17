package com.chat;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;
import org.slf4j.Logger;

@ServerEndpoint("/chat")
public class ChatManager {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ChatManager.class);

    private static final Map<String, Map<String, User>> rooms = new ConcurrentHashMap<>();
    private static final String DUPLICATE_MSG = "{\"type\":\"system\",\"message\":\"Ya existe un usuario con ese nombre\"}";

    private User user;

    @OnOpen
    public void open(Session session) {
        this.user = new User(session);
        logger.info("New session opened: "+session.getId());
    }

    @OnMessage
    public void handleMessage(Session session, String message) throws IOException {

        logger.info(message);

        JSONObject jsonMessage = new JSONObject(message);        

        if(!this.user.isValid() && !jsonMessage.optString("name").isEmpty()){
            newUser(jsonMessage);
        }else{
            // Broadcast message
            rooms.get(jsonMessage.getString("chat")).values().forEach( _user ->
                _user.send(message)
            );
        }

    }

    @OnClose
    public void close(Session session){

        if(this.user.isValid()){
            Map<String, User> chat = rooms.get(this.user.getChat());
            chat.remove(this.user.getName());

            if(chat.isEmpty()){
                // Remove chat room if it's empty
                rooms.remove(this.user.getChat());
            }
        }

        logger.info("Sesión cerrada: "+this.user.getName());

    }

    @OnError
    public void onError(Session session, Throwable thr) {

        logger.error("Cliente "+session.getId()+", error: "+thr.getMessage());
        thr.printStackTrace();

    }

    private void newUser(JSONObject jsonMessage){

        String chat = jsonMessage.getString("chat");
        String name = jsonMessage.getString("name");

        if( this.userExist(name) ){
            this.user.send(DUPLICATE_MSG);
        }else{
            if(!rooms.containsKey(chat)){
                // Chat doesn't exist
                rooms.put(chat, new ConcurrentHashMap<>());
            }
            this.user.setUp(name, chat);
            rooms.get(chat).put(name, this.user);
        }

    }

    private boolean userExist(String user_name){
        return rooms.values().stream().anyMatch((chat) ->
            chat.containsKey(user_name)
        );
    }

}
