package com.ns.service.impl;

import com.ns.entity.OrderMonth;
import com.ns.entity.OrderYear;
import com.ns.repository.OrderYearRepository;
import com.ns.service.OrderMonthService;
import com.ns.service.OrderYearService;
import com.ns.vo.OrderMonthVo;
import com.ns.vo.OrderYearVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderYearServiceImpl implements OrderYearService {
    @Resource
    OrderYearRepository orderYearRepository;
    @Autowired
    OrderMonthService orderMonthService;

    @Override
    @Transactional
    public int save(OrderYear orderYear) {
        return orderYearRepository.save(orderYear);
    }

    @Override
    public OrderYear selectData(String yearData) {
        return orderYearRepository.selectData(yearData);
    }

    @Override
    public List<OrderYearVo> findAll() {
        List<OrderYearVo> orderYearVOS = new ArrayList<>();
        List<OrderYear> orderYears=orderYearRepository.findAll();
        orderYears.stream().forEach(oys ->{
            List<OrderMonthVo> orderMonthVOS = new ArrayList<>();
            OrderYearVo orderYearVo=new OrderYearVo();
            orderYearVo.setData(oys.getData());
            orderYearVo.setOrder_payment((double) oys.getOrder_payment()/100);
            orderYearVo.setOrder_num(oys.getOrder_num());
            List<OrderMonth> oms=orderMonthService.findDate(oys.getData());
            oms.stream().forEach(om -> {
                OrderMonthVo orderMonth=new OrderMonthVo();
                orderMonth.setData(om.getData());
                orderMonth.setOrder_payment((double) om.getOrder_payment()/100);
                orderMonth.setOrder_num(om.getOrder_num());
                orderMonthVOS.add(orderMonth);
            });
            orderYearVo.setOrderMonthVoList(orderMonthVOS);
            orderYearVOS.add(orderYearVo);
        });
        return orderYearVOS;
    }

    @Override
    @Transactional
    public void update(OrderYear oy) {
        orderYearRepository.update(oy);
    }
}
