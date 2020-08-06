package com.ns.repository;

import com.ns.entity.Admin;

public interface AdminRepository {
    Admin login(String username, String password);

    Admin findByName(String name);
}
