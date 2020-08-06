package com.ns.dto;

import lombok.Data;

@Data
public class MenuDto {
    private long id;
    private String name;
    private double price;
    private String flavor;
    private TypeDto type;
    private String pathurl;
}
