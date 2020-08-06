package com.ns.service.Impl;

import com.ns.dto.MenuDto;
import com.ns.repository.MenuRepository;
import com.ns.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
   @Resource
    private MenuRepository menuRepository;



    @Override
    public int count() {
        return menuRepository.count();
    }

    @Override
    public MenuDto findById(long id) {
        return menuRepository.findById(id);
    }

    @Override
    public List<MenuDto> findAll(int index, int limit) {
        return menuRepository.findAll((index-1)*limit,limit);
    }
}
