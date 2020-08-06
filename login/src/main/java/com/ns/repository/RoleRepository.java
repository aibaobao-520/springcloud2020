package com.ns.repository;

import com.ns.dto.RoleDto;

import java.util.List;

public interface RoleRepository {

    RoleDto findById(long id);

}
