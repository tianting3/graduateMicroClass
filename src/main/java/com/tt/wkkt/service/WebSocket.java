package com.tt.wkkt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
@ServerEndpoint("/webSocket")
@Component
@Slf4j
public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSocketSet=new CopyOnWriteArraySet<>();

    @OnOpen
    public void opOpen(Session session){
        this.session=session;
        webSocketSet.add(this);
        log.info("[websocket消息]有新的连接，总数：{}",webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("[websocket消息]断开连接，总数：{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("[websocket消息]收到客户端发来的消息：{}",message);
        if (message.equals("true")){
            sendMessage("开始抢答");
        }else {
            sendMessage("停止抢答");
        }

    }

    /*发送消息*/
    public void sendMessage(String message){

        for (WebSocket webSocket:webSocketSet){
            log.info("[websocket消息]广播消息，message={}",message);
            try{
                webSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
