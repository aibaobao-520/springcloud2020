package com.ns.task;

import com.ns.dto.OrderDto;
import com.ns.entity.OrderMonth;
import com.ns.entity.OrderYear;
import com.ns.service.OrderMonthService;
import com.ns.service.OrderService;
import com.ns.service.OrderYearService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ns.util.DateUtils.getMonthBegin;
import static com.ns.util.DateUtils.getMonthEnd;


@Component
public class MoneyTask {
    //月统计 和 年统计的 定时器
    @Autowired
    OrderMonthService orderMonthService;
    @Autowired
    OrderYearService orderYearService;
    @Autowired
    OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 每隔10秒执行, 单位：ms。
     */
  /* @Scheduled(fixedRate = 10000)
    public void testFixRate() throws ParseException {
         String time=dateFormat.format(new Date());
            String createTime=getMonthBegin(time) ;
            String closeTime=getMonthEnd(time) ;
            OrderDto o=orderService.findOrderMonth(createTime,closeTime);

            OrderMonth bo=orderMonthService.findDate(createTime.substring(0,7));
            if(bo==null){
                OrderMonth orderMonth=new OrderMonth();
                orderMonth.setOrder_num(o.getOrder_num());
                orderMonth.setOrder_payment(o.getOrder_payment());
                orderMonth.setData(createTime.substring(0,7));
                int i=orderMonthService.save(orderMonth);
                if(i>0){
                logger.info("执行成功");
               }else{
                logger.info("执行失败");
               }
            }else{
                return;
            }

    }*/

    //@Scheduled(fixedRate = 10000)
 /*   @Scheduled(cron="0 59 23 31 12 *")//年底执行
    public void exOrderYear() {
        String time=dateFormat.format(new Date());
        String yearData=time.substring(0,4);
        OrderYear oy=orderYearService.selectData(yearData);
        if(oy==null){
        OrderDto o=orderMonthService.findYearDate(yearData);
        OrderYear orderYear=new OrderYear();
        orderYear.setData(yearData);
        orderYear.setOrder_num(o.getOrder_num());
        orderYear.setOrder_payment(o.getOrder_payment());
        int i=orderYearService.save(orderYear);
        if(i>0){
            logger.info("执行成功");
        }else{
            logger.info("执行失败");
        }
        }else{
            return;
        }
    }*/
    @Scheduled(cron = "0 59 23 28-31 * ?")//每月的月底执行
    public void exOrderMonth() {
        final Calendar c = Calendar.getInstance();
        if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
            //是最后一天
            String time=dateFormat.format(new Date());
            String createTime=getMonthBegin(time) ;
            String closeTime=getMonthEnd(time) ;
            OrderDto o=orderService.findOrderMonth(createTime,closeTime);
            List<OrderMonth> bo=orderMonthService.findDate(createTime.substring(0,7));
            if(!bo.isEmpty()){
                OrderMonth orderMonth=new OrderMonth();
                orderMonth.setOrder_num(o.getOrder_num());
                orderMonth.setOrder_payment(o.getOrder_payment());
                orderMonth.setData(createTime.substring(0,7));
                int i=orderMonthService.save(orderMonth);
                if(i>0){
                    //进行添加到year
                    String yearData=time.substring(0,4);
                    OrderYear oy=orderYearService.selectData(yearData);
                    if(oy==null){
                        //月初第一个月
                        OrderDto od=orderMonthService.findYearDate(yearData);
                        OrderYear orderYear=new OrderYear();
                        orderYear.setData(yearData);
                        orderYear.setOrder_num(o.getOrder_num());
                        orderYear.setOrder_payment(o.getOrder_payment());
                        orderYearService.save(orderYear);
                    }else{
                        //修改 追加
                        oy.setOrder_num(oy.getOrder_num()+o.getOrder_num());
                        oy.setOrder_payment(oy.getOrder_payment()+o.getOrder_payment());
                        orderYearService.update(oy);
                    }

                logger.info("执行成功");
               }else{
                logger.info("执行失败");
               }
            }else{
                return;
            }
        }
    }

}



