package com.ns.repository;

import com.ns.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll(Integer page,Integer limit);
    User findById(long id);
  void save(User user);
   void update(User user);
  void delete(long id);
     User findByUserName(String username);


  Integer findAllCount();
}
