package com.ns.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ns.entity.User;
import com.ns.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
/*@DefaultProperties(defaultFallback = "defaultfallback")//统一配置降级方法*/
public class OrderFeignController {

    @Autowired
    private OrderFeign orderFeign;
/*    @HystrixCommand(fallbackMethod = "fallback")*/
    @GetMapping("/hello/{name}")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public String hiService(@PathVariable String name) {
        return orderFeign.hiService(name);
    }
 /*   //fallback逻辑
    private String fallback(@PathVariable String name){
        return "fallback";
    }*/
 //注意，方法签名一定要要和api方法一致
 private Object saveOrderFail(int userId, int productId){

     System.out.println("controller中的降级方法");

     Map<String, Object> msg = new HashMap<>();
     msg.put("code", -1);
     msg.put("msg", "抢购人数太多，您被挤出来了，稍等重试");
     return msg;
 }
   /* public String defaultfallback(){
        return "默认提示,太拥挤了，稍后再试...";
    }*/


}
