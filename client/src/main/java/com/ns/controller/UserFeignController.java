package com.ns.controller;

/*import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
/*@RabbitListener(queues = "rabbit" )*/
public class UserFeignController {
  /*  @Autowired
    private AmqpTemplate rabbitmqTemplate;*/
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
   /* @RabbitHandler
    public void recieved(String string) {
        System.out.println(string);
    }*/


}
