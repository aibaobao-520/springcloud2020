package com.ns.service.Impl;

import com.ns.dto.UserDto;
import com.ns.repository.UserRepository;
import com.ns.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Override
    public UserDto login(String username, String password, HttpSession session) {
        UserDto u=userRepository.login(username,password);
        session.setAttribute("user",u);
        return u;
    }

    @Override
    public UserDto findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
