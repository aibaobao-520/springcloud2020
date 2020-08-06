package com.ns.entity;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    List<Menu> menus;
    Integer pageNum;
    Integer totalPage;
    Integer total;
}
