package com.ns.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderYearVo {
    private  int order_num;       //订单数量
    private  double  order_payment; //总金额
    private  String  data;         //这个年的时间 比如2020
    List<OrderMonthVo> orderMonthVoList;
}
