package com.ns.repository;



import com.ns.entity.OrderItem;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderItemRepository {
    List<OrderItem> findAll();
    OrderItem findById(long id);
    void save(OrderItem orderItem);
    void update(OrderItem orderItem);
    void delete(long id);

    List<OrderItem> findByOid(String  id);

    int deleteByOid(String id);
}
