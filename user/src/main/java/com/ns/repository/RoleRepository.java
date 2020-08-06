package com.ns.repository;

import com.ns.entity.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleRepository {

    Role findById(long id);

    List<Role> findAll();
}
