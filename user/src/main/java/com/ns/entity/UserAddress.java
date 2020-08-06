package com.ns.entity;

import lombok.Data;

@Data
public class UserAddress {
    private  int id ;
    private int user_id;
    private String  name;
    private String  address;
    private  boolean isDefault;
}
