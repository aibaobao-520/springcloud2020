package com.ns.service.Impl;

import com.ns.entity.Role;
import com.ns.repository.RoleRepository;
import com.ns.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(long id) {
        return roleRepository.findById(id);
    }
}
