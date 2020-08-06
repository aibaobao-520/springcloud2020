package com.ns.service;

import com.example.apijson.entity.Page;
import com.ns.entity.Order;

import java.util.Map;

public interface OrderService {
    Order findById(String id);
    void save(Order order);
    void update(Order order);
    int  delete(String id);

    int count();
    Page findPage(int index, int limit);

    void  saveShipping(String data);


    void saveOrder(Map<String, Object> o, Integer userId);

    void updateMoney(Map map);
}
