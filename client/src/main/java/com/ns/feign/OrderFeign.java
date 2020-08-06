package com.ns.feign;

import com.ns.feign.Impl.OrderServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "order",fallback = OrderServiceImpl.class)
@Component
/**
 * feign远程调用接口
 * FeignClient注解的name属性值要写服务提供者在注册中心注册的服务名称
 * FeignClient注解的fallback属性值表示远程调用失败时的回调类
 */
public interface OrderFeign {
   @GetMapping("/order/hello/{name}")
   String hiService(@PathVariable String name);
}
