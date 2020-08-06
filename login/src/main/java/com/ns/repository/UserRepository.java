package com.ns.repository;

import com.ns.dto.UserDto;

public interface UserRepository {
   UserDto login(String username, String password);
   UserDto findByUserName(String username);
}
