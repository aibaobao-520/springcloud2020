package com.ns.repository;

import com.ns.dto.MenuDto;

import java.util.List;

public interface MenuRepository{
    public List<MenuDto> findAll(int index, int limit);
    public int count();
    public MenuDto findById(long id);
}
