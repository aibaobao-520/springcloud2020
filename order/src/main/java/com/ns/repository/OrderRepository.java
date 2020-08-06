package com.ns.repository;
import com.ns.entity.Order;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderRepository {
    Order findById(String id);
    void save(Order order);
    void update(Order order);
    int  delete(String id);

    int count();

    List<Order> findAll(int page, int limit);
}
