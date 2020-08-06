package com.ns.service.impl;

import com.ns.dto.OrderDto;
import com.ns.entity.OrderMonth;
import com.ns.repository.OrderMonthRepository;
import com.ns.service.OrderMonthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OrderMonthServiceImpl implements OrderMonthService {
    @Resource
    OrderMonthRepository orderMonthRepository;


    @Override
    public List<OrderMonth> findAll() {
        return orderMonthRepository.findAll();
    }



    @Override
    public List<OrderMonth> findDate(String substring) {
        return  orderMonthRepository.findDate(substring+'%');
    }

    @Override
    public int save(OrderMonth orderMonth) {
        return  orderMonthRepository.save(orderMonth);
    }

    @Override
    public OrderDto findYearDate(String substring) {
        return  orderMonthRepository.findYearDate(substring+'%');
    }
}
