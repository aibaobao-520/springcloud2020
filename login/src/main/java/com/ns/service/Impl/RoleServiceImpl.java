package com.ns.service.Impl;

import com.ns.dto.RoleDto;
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
    public RoleDto findById(long id) {
        return roleRepository.findById(id);
    }
}
