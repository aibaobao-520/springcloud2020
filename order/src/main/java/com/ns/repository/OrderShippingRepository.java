package com.ns.repository;

import com.ns.entity.OrderShipping;

import java.util.List;

public interface OrderShippingRepository {
    List<OrderShipping> findAll();
    OrderShipping findById(long id);
    void save(OrderShipping orderShipping);
    void update(OrderShipping orderShipping);
    void delete(long id);
    OrderShipping findByOId(String id);

    int deleteByOid(String id);
}
