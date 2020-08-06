package com.ns.repository;

import com.ns.dto.UserDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserRepository  extends Mapper<UserDto> {
  List<UserDto> findAll();
     UserDto findById(long id);
    UserDto findByUserName(String username);


    String findByName(String name);

    List<UserDto> findByShippingAll(int shippingId);
}
