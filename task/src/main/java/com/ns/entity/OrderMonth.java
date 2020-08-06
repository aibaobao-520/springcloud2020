package com.ns.entity;

import lombok.Data;

@Data
public class OrderMonth {
    private  int id;
    private  int order_num;       //订单数量
    private  int order_payment; //总金额
    private  String  data;         //这个月的时间 比如2020
}
