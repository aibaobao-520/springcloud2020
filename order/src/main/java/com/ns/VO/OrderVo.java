package com.ns.VO;

import com.ns.dto.UserDto;
import com.ns.entity.OrderItem;
import com.ns.entity.OrderShipping;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class OrderVo {
    private String  id	; //				订单id
    private UserDto user		; //		用户id
    private double payment		; //		实付金额
    private int payment_type		; //			支付类型1在线2货到付款
    private int state ; //	未付款2已付款3未发货4已发货5交易成功6交易关闭
    private Date create_time	; //					订单创建时间
    private Date update_time		; //		订单更新时间
    private Date payment_time	; //				付款时间
    private Date consgin_time	; //				发货时间
    private Date end_time	; //			交易完成时间
    private Date close_time		; //				交易关闭时间
    private String shipping_name		; //		送货人
    private String shipping_code		; //				单号
    private List<OrderItem> orderItemList;                  //订单详情
    private OrderShipping orderShipping;               //送餐人的表



}
