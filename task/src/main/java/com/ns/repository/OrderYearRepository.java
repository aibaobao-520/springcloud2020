package com.ns.repository;

import com.ns.entity.OrderYear;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderYearRepository {
    int save(OrderYear orderYear);

    OrderYear selectData(String yearData);

    List<OrderYear> findAll();

    void update(OrderYear oy);
}
