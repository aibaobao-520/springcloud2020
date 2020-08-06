package com.ns.service;

import com.ns.entity.Order;
import com.ns.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAll();
    OrderItem findById(long id);
    void save(OrderItem orderItem);
    void update(OrderItem orderItem);
    void delete(long id);
    List<OrderItem> findByOid(String id);

   int  deleteByOid(String id);
}
