package com.ns.service;

import com.ns.dto.MenuDto;

import java.util.List;

public interface MenuService {
    public int count();
    public MenuDto findById(long id);

    List<MenuDto> findAll(int index, int limit);
}
