package com.ns.service.impl;


import com.ns.dto.OrderDto;
import com.ns.repository.OrderRepository;

import com.ns.service.OrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;


    @Override
    public OrderDto findOrderMonth(String createTime, String closeTime) {
        return orderRepository.findOrderMonth(createTime,closeTime);
    }
}
