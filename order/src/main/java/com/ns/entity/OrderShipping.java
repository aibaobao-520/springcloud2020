package com.ns.entity;

import lombok.Data;

import java.util.Date;
@Data
public class OrderShipping {
  private int id;
  private Order oid;//订单id
  private String  receiver_name;//收货人姓名
  private String  receiver_phone;//固定电话
  private String  receiver_mobile;//移动电话
  private String  receiver_provinces; //省份
  private String  receiver_city;  //城市
  private String  receiver_areas;  //区/县
  private String  receiver_address;  //收货地址
  private Date create_time;//订单配送时间
  private Date update_time;//订单更新时间


}

