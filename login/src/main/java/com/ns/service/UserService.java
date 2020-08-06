package com.ns.service;

import com.ns.dto.UserDto;

import javax.servlet.http.HttpSession;

public interface UserService {
    UserDto login(String username, String password, HttpSession session);
    UserDto findByUserName(String username);
}
