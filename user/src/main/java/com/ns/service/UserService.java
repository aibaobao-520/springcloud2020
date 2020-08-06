package com.ns.service;

import com.example.apijson.entity.Page;
import com.ns.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll(Integer page,Integer limit);
    User findById(long id);
    void save(User user);
    void update(User user);
    void delete(long id);

    User findByUserName(String username);

    Integer findAllCount();

    Page findPage(Integer page, Integer limit);
}
