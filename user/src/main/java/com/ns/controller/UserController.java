package com.ns.controller;

import com.example.apijson.entity.Page;
import com.example.apijson.entity.ResultData;
import com.ns.entity.Role;
import com.ns.entity.User;
import com.ns.service.RoleService;
import com.ns.service.UserService;
import lombok.extern.slf4j.Slf4j;
/*import org.springframework.amqp.core.AmqpTemplate;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
  /*  @Autowired
    private AmqpTemplate rabbitTemplate;*/

    /**
     * 分页所有用户
     * @return
     */
    @GetMapping(value = "/findAllPage")
    public ResultData findAll(Integer page,Integer limit,Integer state){
        log.info("分页查询所有用户");
        try {
                  Page page2=userService.findPage(page,limit);
                  if(page2.getTotal()!=null){
                      return new ResultData(200,"查询成功",page2);
                  }else{
                  return new ResultData(444,"无数据",page2);
                  }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"查询失败",e.toString());
        }

    }

    @GetMapping(value = "/findRoleAll")
    public ResultData findRoleAll(){
        try {
           List<Role> roles=roleService.findAll();
            return new ResultData(200,"查询成功",roles);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"查询失败",e.toString());
        }

    }

    @PostMapping(value = "/save")
    public ResultData saveUser(@RequestBody User user){
        try {
            userService.save(user);
            return new ResultData(200,"添加成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"添加失败",e.toString());
        }
    }
    @PostMapping(value = "/update")
    public ResultData updateUser(@RequestBody User user){
        try {
            userService.update(user);
            return new ResultData(200,"修改成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"修改失败",e.toString());
        }
    }
    @GetMapping(value = "/findById/{id}")
    public ResultData findUser(@PathVariable Integer id){
        try {
            User u=userService.findById(id);
            return new ResultData(200,"查询成功",u);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"查询失败",e.toString());
        }
    }
    @GetMapping(value = "/delete/{id}")
    public ResultData deleteUser(@PathVariable Integer id){
        try {
            userService.delete(id);
            return new ResultData(200,"删除成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"删除失败",e.toString());
        }
    }
    @GetMapping(value = "/soleUsername/{username}")
    public ResultData deleteUser(@PathVariable String  username) {
        try {
            User u= userService.findByUserName(username);
            if(u==null){
                return new ResultData(200, "该用户名可以注册", true);
            }else{
            return new ResultData(200, "该用户名可以已存在请重新输入", false);
            }
         } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"用户出现异常",e.toString());
         }

    }

  /*  @GetMapping(value = "/sendRabbitmq")
    public void sendRabbitmq() {
      String context="你好";
      rabbitTemplate.convertAndSend("rabbit",context);
    }
*/


}
