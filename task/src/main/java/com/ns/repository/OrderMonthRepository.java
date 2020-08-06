package com.ns.repository;

import com.ns.dto.OrderDto;
import com.ns.entity.OrderMonth;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMonthRepository  extends BaseMapper<OrderMonth> {
    List<OrderMonth> findAll();

    List<OrderMonth> findDate(String substring);

    int save(OrderMonth orderMonth);

    OrderDto findYearDate(String s);


}
