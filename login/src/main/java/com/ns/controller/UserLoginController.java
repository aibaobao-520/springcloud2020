package com.ns.controller;

import com.example.apijson.entity.ResultData;
import com.ns.entity.Admin;
import com.ns.dto.UserDto;
import com.ns.service.AdminService;
import com.ns.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

   @PostMapping("/login")
   public ResultData login(@RequestBody Map map, HttpSession httpSession){
      try {
           Subject subject = SecurityUtils.getSubject();
           UsernamePasswordToken token = new UsernamePasswordToken((String)map.get("userName"),(String)map.get("passWord"));
           subject.login(token);
           if(map.get("userName").equals("admin")){
               Admin admin=(Admin) subject.getPrincipal();
               subject.getSession().setAttribute("UserDto ",admin );
               return  new ResultData(200,"管理员登录成功",admin);
           }else{
           UserDto UserDto  = (UserDto ) subject.getPrincipal();
               subject.getSession().setAttribute("UserDto ",UserDto );
               return  new ResultData(200,"用户登录成功",UserDto);
           }

       } catch (UnknownAccountException e) {
           e.printStackTrace();
           return new ResultData(400, e.getMessage(), "user");
       } catch (IncorrectCredentialsException e){
           e.printStackTrace();
           return new ResultData(400, e.getMessage(), "user");
       }
     /* try {
           if(map.get("userName").equals("admin")){

               Admin admin=adminService.login((String)map.get("userName"),(String)map.get("passWord"),httpSession);
               if(admin==null){
                   return  new ResultData(400,"管理员密码错误","admin");
               }else{
               return  new ResultData(200,"管理员登录成功","admin");
               }
           }
           else{
               UserDto user = userService.login((String) map.get("userName"), (String) map.get("passWord"), httpSession);
               if(user==null){
                   return new ResultData(400, "用户密码错误", "user");
               }else{
                   return new ResultData(200, "用户登录成功", "user");
               }

           }

       } catch (Exception e) {
           e.printStackTrace();
           return  new ResultData(444,"用户名或者密码错误 ，请重新输入",false);
       }   */
   }
    @GetMapping(value = "/soleUsername/{username}")
    public ResultData deleteUser(@PathVariable String  username) {
        if(username.equals("admin")){
            return new ResultData(200, "", true);
        }else{
        UserDto u= userService.findByUserName(username);
        if(u==null){
            return new ResultData(200, "该用户名不存在，请先注册", false);
        }else{
            return new ResultData(200, "", true);
        }


    }}
    @GetMapping(value = "/logout")
    public  ResultData  logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultData(200, "退出登录成功", true);
    }

}
