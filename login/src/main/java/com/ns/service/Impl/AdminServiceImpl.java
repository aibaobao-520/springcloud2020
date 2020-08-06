package com.ns.service.Impl;

import com.ns.entity.Admin;
import com.ns.repository.AdminRepository;
import com.ns.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {
@Resource
private AdminRepository adminRepository;

    @Override
    public Admin login(String username, String password) {
        Admin a=adminRepository.login(username,password);
        return a;
    }

    @Override
    public Admin findByName(String name) {
        return adminRepository.findByName(name);
    }
}
