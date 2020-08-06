package com.ns.config;
/*import org.springframework.amqp.core.*;*/
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * rabbitmq的配置类
 */
@Component
public class RabbitConfig {
    /**
     * 定义队列名
     */
    private final static String STRING = "string";


    /**
     * 定义string队列
     * @return
     */
  /*  @Bean
    public Queue string() {
        return new Queue(STRING);
    }*/
}
