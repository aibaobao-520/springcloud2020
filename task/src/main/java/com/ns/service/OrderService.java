package com.ns.service;


import com.ns.dto.OrderDto;

import java.util.Date;

public interface OrderService {

    OrderDto findOrderMonth(String createTime,String closeTime);

}
