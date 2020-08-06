package com.ns.service;

import com.example.apijson.entity.Page;
import com.ns.config.OSSConfig;
import com.ns.entity.Menu;
import com.ns.vo.MenuTypeVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {
    public int count();
    public Menu findById(long id);
    public void save(Menu menu);
    public void deleteById(long id);

    List<Menu> findAll(int index, int limit);

    void update(Menu menu);

    String uploadFile(MultipartFile file, OSSConfig ossConfig) throws Exception;

    Page findPage(int index, int limit);

    List<Menu> findByMenu(long id);

    List<MenuTypeVo> findByMenuTypeVo();
}
