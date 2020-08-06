package com.ns.entity;

import lombok.Data;

@Data
public class Menu {
    private long id;
    private String name;
    private double price;
    private String flavor;
    private String desc;
    private Type type;
    private String pathurl;
}
