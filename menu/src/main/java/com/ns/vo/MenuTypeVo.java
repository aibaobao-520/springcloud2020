package com.ns.vo;

import com.ns.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTypeVo {
    private  long id ;
    private  String name;
    private  List<Menu> menuList;


}
