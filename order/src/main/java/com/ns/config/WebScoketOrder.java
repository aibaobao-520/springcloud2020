package com.ns.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/websocket/{admin}/{userId}")
public class WebScoketOrder {

    /**
     * 房间号 -> 组成员信息
     */
    private static ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();
    /**
     * 房间号 -> 在线人数
     */

    private static ConcurrentHashMap<String, Set<Integer>> onlineUserMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Map<Integer,String>> adminOrderMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Map<Integer,Session>> adminMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Map<Integer,Session>> UserMap = new ConcurrentHashMap<>();
    /**
     * 建立连接调用的方法
     *  存入用户
     * @param session
     * @param admin
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("admin") String admin, @PathParam("userId") Integer userId) {
        if(userId==0){
       //管理员登录 目前就一个
            Map<Integer,Session> sessionList = adminMap.computeIfAbsent(admin, k -> new HashMap<>());
            sessionList.put(userId,session);
        }else{
            //用户
        Map<Integer,Session> sessionList = UserMap.computeIfAbsent(admin, k -> new HashMap<>());
        sessionList.put(userId,session);
        }
    }
    /**
     * 关闭连接调用的方法
     *
     * @param session
     * @param admin
     */
    @OnClose
    public void onClose(Session session, @PathParam("admin") String admin, @PathParam("userId") Integer userId) {
        if (userId == 0) {
            //管理员退出
            Map<Integer, Session> sessionList = adminMap.get(admin);
            Iterator<Integer> iterator = sessionList.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = iterator.next();
                if (key == userId) {
                    iterator.remove();
                }
            }
        } else {
            //用户
            Map<Integer, Session> sessionList = UserMap.get(admin);
            Iterator<Integer> iterator = sessionList.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = iterator.next();
                if (key == userId) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     *
     * 收到消息调用的方法，用户发送消息
     *
     * @param admin:admin
     * @param userId：用户id
     * @param message：发送消息
     */
    @OnMessage
    public void onMessage(@PathParam("admin") String admin, @PathParam("userId") Integer userId, String message) throws IOException {
        Map<Integer, Session> sessionList = UserMap.get(admin);
        Session session=sessionList.get(userId);
        Map<Integer, String> userOrder = adminOrderMap.computeIfAbsent(admin, k -> new HashMap<>());
        userOrder.put(userId,message);   //存入adminOrderMap
        sendMessage(session, message);  //发送信息
    }
    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
                System.out.println("发送数据：" + message);
                session.getBasicRemote().sendText(message);

            }
        }
    }

    /**
     * 传输消息错误调用的方法
     *
     *
     * @param error
     */
    @OnError
    public void OnError(Throwable error) {
        log.info("Connection error");
    }



































}
