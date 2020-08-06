package com.ns.repository;

import com.ns.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
public interface MenuRepository extends Mapper<Menu> {
    public List<Menu> findAll(int index, int limit);
    public int count();
    public Menu findById(long id);
    public void save(Menu menu);
    public void update(Menu menu);
    public void deleteById(long id);

    List<Menu> findByMenu(long id);
}
