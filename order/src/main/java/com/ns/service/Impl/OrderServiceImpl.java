package com.ns.service.Impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.apijson.entity.Page;
import com.ns.VO.OrderVo;
import com.ns.dto.MenuDto;
import com.ns.entity.Order;
import com.ns.entity.OrderItem;
import com.ns.repository.OrderRepository;
import com.ns.service.*;
import com.ns.util.OrderUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class OrderServiceImpl  implements OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private UserService  userService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private OrderShippingService orderShippingService;
    @Resource
    private MenuService menuService;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Order findById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        //order.setId(OrderUitl.getBillCode());
        orderRepository.save(order);

    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public int  delete(String id) {

        //发送消息 给客户端
        Order order=orderRepository.findById(id);
        if(order.getState()!=1){
            //退款流程

        }
        //删除订单
       int i=orderItemService.deleteByOid(id);
       int s=orderShippingService.deleteByOid(id);
       if(i==1&&s==1){
          int o= orderRepository.delete(id);
           return o;
       }
       else{
           return 0;
       }
    }

    @Override
    public int count() {
        return  orderRepository.count();
    }

    @Override
    public Page findPage(int index, int limit) {
        Page page = new Page();
        int index2=(index-1)*limit;
        List<Order> orders=orderRepository.findAll(index2,limit);
        if (orders.size() > 0) {
            int c = orderRepository.count();
            page.setTotalPage(c % limit == 0 ? c / limit : c / limit + 1);
            page.setTotal(c);
            page.setPageNum(index);
            List<OrderVo> orderVos=new ArrayList<>();
            for (Order order : orders) {
                OrderVo orderVo=new OrderVo();
                orderVo.setId(order.getId());
                //生成订单的时候 是乘100
                orderVo.setPayment((double)order.getPayment()/100);
                orderVo.setPayment_type(order.getPayment_type());
                orderVo.setState(order.getState());
                orderVo.setClose_time(order.getClose_time());
                orderVo.setConsgin_time(order.getConsgin_time());
                orderVo.setUpdate_time(order.getUpdate_time());
                orderVo.setCreate_time(order.getCreate_time());
                orderVo.setEnd_time(order.getEnd_time());
                orderVo.setPayment_time(order.getPayment_time());
                orderVo.setShipping_name(order.getShipping_name());
                orderVo.setShipping_code(order.getShipping_code());
                orderVo.setUser(userService.findById(order.getUser_id()));
                orderVo.setOrderItemList(orderItemService.findByOid(order.getId()));
                orderVo.setOrderShipping(orderShippingService.findByOId(order.getId()));
                orderVos.add(orderVo);
            }
            page.setT(orderVos);
        }
        return  page;
    }

    @Override
    public void saveShipping(String data) {
        JSONObject jsonObject= JSON.parseObject(data);
       Order order= orderRepository.findById(jsonObject.getString("oid"));
       order.setShipping_name(jsonObject.getString("sname"));
       order.setShipping_code(OrderUitl.getBillCode());
       order.setState(3);
       orderRepository.update(order);

    }
    //用户下单
    @Override
    public void saveOrder(Map<String, Object> o, Integer userId) {
        Order order=new Order();
        String tid=OrderUitl.getBillCode();
        order.setId(tid);
        order.setUser_id(userId);
        order.setState(1);
        order.setCreate_time(new DateTime());
        orderRepository.save(order);
        for(Map.Entry<String,  Object> entry : o.entrySet()){
            OrderItem orderItem=new OrderItem();
            String menuKey = entry.getKey();
            Integer numValue = (Integer) entry.getValue();
            orderItem.setMid(menuKey);
            orderItem.setNum(numValue);
            orderItem.setOid(order);
            MenuDto m=menuService.findById(Integer.valueOf(menuKey));
            orderItem.setPic_path(m.getPathurl());
            orderItem.setTitle(m.getName());
            orderItem.setTotal_fee(m.getPrice());
            orderItemService.save(orderItem);
        }
        //清空购物车
        redisTemplate.delete(userId);
        //用户30分钟支付
        redisTemplate.opsForValue().set(userId, tid, 1800, TimeUnit.SECONDS);
    }
    //支付成功
    @Override
    public void updateMoney(Map map) {
        redisTemplate.delete(map.get("userId"));
        //清除时间
        Order order=orderRepository.findById((String) map.get("orderId"));
        order.setState(2);
        order.setUpdate_time(new DateTime());
        order.setPayment_time(new DateTime());
        order.setPayment((int) ((double) map.get("payment")*100));
        orderRepository.update(order);

    }


}
