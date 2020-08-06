package com.ns.service;


import com.ns.entity.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> findById(int user_id);
    int save(UserAddress userAddress);
    int update(UserAddress userAddress);
}
