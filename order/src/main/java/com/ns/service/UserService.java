package com.ns.service;

import com.ns.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(long id);

    UserDto findByUserName(String username);

    String findByName(String name);

    List<UserDto> findByShippingAll(int shippingId);
}
