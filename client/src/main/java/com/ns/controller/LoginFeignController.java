package com.ns.controller;

import com.ns.entity.ResultData;
import com.ns.feign.LoginFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class LoginFeignController {
    @Resource
    private LoginFeign loginFegin;


    @PostMapping("/login")
    public ResultData login(@RequestParam("userName") String username, @RequestParam("passWord") String password){
        try {
            if(username.equals("admin")){
                Boolean  boolea=loginFegin.login(username,password);
                return  new ResultData(200,"管理员登录成功","admin");
            }
            else{
                Boolean  boolea=loginFegin.login(username,password);
                return  new ResultData(200,"用户登录成功","user");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultData(444,"用户名或者密码错误 ，请重新输入",false);
        }
    }


}
