package com.ns.controller;


import com.example.apijson.entity.ResultData;
import com.ns.config.WebScoketOrder;
import com.ns.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/websocket")
public class WebSocketController {
    private  static  String Message = "有用户下单，请查看";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WebScoketOrder webSocket;
    @GetMapping("/{admin}/{userId}")
    public ResultData sendOneWebSocketAdmin(@PathVariable String admin , @PathVariable Integer userId) throws IOException {
        log.info("生成订单");
        try {
            webSocket.onMessage(admin,userId,Message);//发送消息
            Map<String, Object> o =  redisTemplate.opsForHash().entries(userId);
            orderService.saveOrder(o,userId);
            return new ResultData(200, "下单成功,请30分钟内支付", true);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultData(400, "下单失败",false);
        }

    }
}
