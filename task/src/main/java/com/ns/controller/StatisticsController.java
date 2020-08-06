package com.ns.controller;

import com.example.apijson.entity.ResultData;
import com.ns.entity.OrderYear;
import com.ns.service.OrderMonthService;
import com.ns.service.OrderYearService;
import com.ns.vo.OrderYearVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time")
public class StatisticsController {
    @Autowired
    OrderMonthService orderMonthService;
    @Autowired
    OrderYearService orderYearService;

    //查询年份
    @GetMapping("/selectOrderYearAll")
    public ResultData selectOrderYearAll(){
        try {
            List<OrderYearVo> orderYearVos = orderYearService.findAll();
            return new ResultData(200,"查询成功",orderYearVos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400,"查询失败",false);
        }

    }
    //点击月份 查询每天的订单
    @GetMapping("/selectOrderDayAll")
    public ResultData selectOrderDayAll(){
        try {
            List<OrderYearVo> orderYearVos = orderYearService.findAll();
            return new ResultData(200,"查询成功",orderYearVos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(400,"查询失败",false);
        }

    }

}
