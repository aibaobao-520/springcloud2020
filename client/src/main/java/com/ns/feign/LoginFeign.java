package com.ns.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "login")
@Component
public interface LoginFeign {
    @PostMapping("/user/login")
    boolean login(@RequestParam("userName") String username, @RequestParam("passWord") String password);


}
