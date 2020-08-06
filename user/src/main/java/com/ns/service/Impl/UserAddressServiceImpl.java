package com.ns.service.Impl;


import com.ns.entity.UserAddress;
import com.ns.repository.UserAddressRepository;
import com.ns.service.UserAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
     @Resource
     UserAddressRepository userAddressRepository;

    @Override
    public List<UserAddress> findById(int user_id) {
        return userAddressRepository.findById(user_id);
    }

    @Override
    @Transactional
    public int save(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    @Override
    @Transactional
    public int update(UserAddress userAddress) {
        return userAddressRepository.update(userAddress);
    }
}
