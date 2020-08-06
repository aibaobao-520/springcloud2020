package com.ns.vo;

import lombok.Data;



@Data
public class OrderMonthVo {
    private  int order_num;       //订单数量
    private  double  order_payment; //总金额
    private  String  data;         //这个月的时间 比如2020-07
}
