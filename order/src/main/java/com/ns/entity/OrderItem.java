package com.ns.entity;


import lombok.Data;

@Data
public class OrderItem {
    private int id ;
    private String mid;   //菜品id
    private Order oid;             //订单id
    private int num;              //数量
    private String title;
    private Double total_fee;
    private String pic_path;
}
