package com.ns.feign.Impl;


import com.ns.entity.User;
import com.ns.feign.OrderFeign;
import org.springframework.stereotype.Component;

@Component
/**
 * 熔断回调类
 * 继承feign远程调用接口，并在实现方法中输出回调的信息
 * 远程调用失败，将会回调该方法
 * @param name 名称
 * @return 自定义返回信息
 */
public class OrderServiceImpl implements OrderFeign {
    @Override
    public String hiService(String name) {
        return name+"网络出现问题，服务暂时不能访问，请稍后访问";
    }
    //秒杀优惠卷  的连接超时
    //
}
