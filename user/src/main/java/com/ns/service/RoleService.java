package com.ns.service;

import com.ns.entity.Role;

import java.util.List;

public interface RoleService {
     List<Role> findAll();
     Role findById(long id);
}
