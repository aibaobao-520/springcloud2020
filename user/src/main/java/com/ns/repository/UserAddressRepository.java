package com.ns.repository;

import com.ns.entity.UserAddress;

import java.util.List;

public interface UserAddressRepository {
    List<UserAddress> findById(int user_id);
    int save(UserAddress userAddress);
    int update(UserAddress userAddress);
}
