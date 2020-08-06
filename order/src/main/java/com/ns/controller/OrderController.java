package com.ns.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.apijson.entity.Page;
import com.example.apijson.entity.ResultData;
import com.ns.entity.OrderItem;
import com.ns.service.OrderItemService;
import com.ns.service.OrderService;
import com.ns.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private UserService userService;
    public static int SHIPPINGS = 2;
    @Value("${server.port}")
    private String port;
    @GetMapping("/index")
    public String index(){
        return "order的端⼝："+this.port;
    }

    @GetMapping("/hello/{name}")
    public String hiService(@PathVariable  String name){

        return userService.findByName(name);
    };

    @GetMapping("/orderAll/{index}/{limit}")
    public ResultData orderAll(@PathVariable("index") int
                                       index, @PathVariable("limit") int limit){
        log.info("分页查询订单");
        Page page=orderService.findPage(index, limit);
        if(page.getTotal()!=null){
            return new ResultData(200, "查询成功", page);
        } else {
            return new ResultData(444, "无数据", false);
        }
    };
    @GetMapping("/orderItemAll")
    public List<OrderItem> orderItemAll(){
        return orderItemService.findAll();
    };

    @GetMapping("/orderUpdate")
    public ResultData orderUpdate(@RequestParam(value = "id") String id){
        log.info("查询订单");
        return new ResultData(200, "查询成功",orderService.findById(id));
    };
    //支付30分钟 redis
     //支付接口    聚合支付
    //生成订单 送餐表
    //删除订单
    //修改订单 分配送货员
    @GetMapping("/orderShippingAll")
    public ResultData orderUpdate(){
        log.info("查询全部送餐人");
        try {
            return new ResultData(200, "查询成功",userService.findByShippingAll(SHIPPINGS));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400, "查询失败",false);
        }
    };

    @PostMapping("/orderSaveShipping")
    public  ResultData orderUpdateShipping(@RequestBody  String data){
        log.info("分配送餐人");
        try {
            orderService.saveShipping(data);
            return new ResultData(200, "分配成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400, "分配失败",false);
        }
    };

    @GetMapping("/orderDelete/{id}")
    public  ResultData orderDelete(@PathVariable  String id){
        log.info("删除订单");
        try {
                int i=orderService.delete(id);
                if(i==1){
                    return new ResultData(200, "删除成功",true);
                }else{
            return new ResultData(400, "删除失败",true);}
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400, "删除失败",false);
        }
    };


    @PostMapping("/orderSave")
    public ResultData orderSave(String json){
        log.info("生成订单");
        try {
            return new ResultData(200, "下单成功，请30分钟内支付",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400, "生成订单失败",false);
        }
    };

    //支付成功
    @PostMapping("/orderSaveMoney")
    public ResultData orderSave(@RequestBody Map map){
        log.info("支付成功");
        try {
            orderService.updateMoney(map);
            return new ResultData(200, "商家已收到",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400, "支付失败",false);
        }
    };
}
