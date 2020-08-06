package com.example.apijson.entity;

import lombok.Data;

import java.util.List;

@Data
public class Page <T> {
    List<T> t;
    Integer pageNum;
    Integer totalPage;
    Integer total;


}
