package com.ns.repository;

import com.ns.dto.OrderDto;

import java.util.Date;

public interface OrderRepository {

    OrderDto findOrderMonth(String createTime, String closeTime);
}
