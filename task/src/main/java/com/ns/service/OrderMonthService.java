package com.ns.service;

import com.ns.dto.OrderDto;
import com.ns.entity.OrderMonth;

import java.util.List;

public   interface OrderMonthService {
    List<OrderMonth> findAll();
    

    List<OrderMonth> findDate(String substring);

    int save(OrderMonth orderMonth);

    OrderDto findYearDate(String substring);
}
