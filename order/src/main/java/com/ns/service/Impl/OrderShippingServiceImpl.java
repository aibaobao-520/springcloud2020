package com.ns.service.Impl;

import com.ns.entity.OrderShipping;
import com.ns.repository.OrderShippingRepository;
import com.ns.service.OrderShippingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OrderShippingServiceImpl implements OrderShippingService {
    @Resource
    private OrderShippingRepository orderShippingRepository;

    @Override
    public List<OrderShipping> findAll() {
        return orderShippingRepository.findAll();
    }

    @Override
    public OrderShipping findById(long id) {
        return orderShippingRepository.findById(id);
    }

    @Override
    public void save(OrderShipping orderShipping) {
        orderShippingRepository.save(orderShipping);
    }

    @Override
    public void update(OrderShipping orderShipping) {
        orderShippingRepository.update(orderShipping);
    }

    @Override
    public void delete(long id) {
        orderShippingRepository.delete(id);
    }

    @Override
    public OrderShipping findByOId(String id) {
        return orderShippingRepository.findByOId(id);
    }

    @Override
    public int deleteByOid(String id) {
        return orderShippingRepository.deleteByOid(id);
    }
}
