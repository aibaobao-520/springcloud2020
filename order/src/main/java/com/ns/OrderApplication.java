package com.ns;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@MapperScan("com.ns.repository")
public class OrderApplication {
    public static void main(String[] args) {
      SpringApplication.run(OrderApplication.class,args);

    }
}
