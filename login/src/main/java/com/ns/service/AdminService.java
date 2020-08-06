package com.ns.service;

import com.ns.entity.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    Admin login(String username, String password);
    Admin findByName(String name);
}
