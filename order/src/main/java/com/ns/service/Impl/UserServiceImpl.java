package com.ns.service.Impl;

import com.ns.dto.UserDto;
import com.ns.repository.UserRepository;
import com.ns.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto findById(long id) {
        return userRepository.findById(id);
    }





    @Override
    public UserDto findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public String findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<UserDto> findByShippingAll(int shippingId) {
        return userRepository.findByShippingAll(shippingId);
    }
}
