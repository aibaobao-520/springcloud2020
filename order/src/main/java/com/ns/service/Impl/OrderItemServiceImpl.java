package com.ns.service.Impl;

import com.ns.entity.OrderItem;
import com.ns.repository.OrderItemRepository;
import com.ns.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    private OrderItemRepository orederItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return orederItemRepository.findAll();
    }

    @Override
    public OrderItem findById(long id) {

        return orederItemRepository.findById(id);
    }

    @Override
    public void save(OrderItem orderItem) {
        orederItemRepository.save(orderItem);
    }

    @Override
    public void update(OrderItem orderItem) {
        orederItemRepository.update(orderItem);
    }

    @Override
    public void delete(long id) {
        orederItemRepository.delete(id);
    }

    @Override
    public List<OrderItem> findByOid(String id) {
        return orederItemRepository.findByOid(id);
    }

    @Override
    public int deleteByOid(String id) {
        return orederItemRepository.deleteByOid(id);
    }
}
