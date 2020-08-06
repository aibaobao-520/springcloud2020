package com.ns.service;

import com.ns.entity.OrderYear;
import com.ns.vo.OrderYearVo;

import java.util.List;

public   interface OrderYearService {
    int save(OrderYear orderYear);

    OrderYear selectData(String yearData);

    List<OrderYearVo> findAll();

    void update(OrderYear oy);
}
