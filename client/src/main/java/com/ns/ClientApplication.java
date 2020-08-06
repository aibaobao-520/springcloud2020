package com.ns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
@EnableCircuitBreaker
/**
 * 服务调用者
 * @ EnableDiscoveryClient 开启客户端发现 能够让Consul注册中心发现
 * @ EnableFeignClients 开启Feign远程调用
 * @ EnableHystrixDashboard 开启熔断监控仪表盘
 * @ EnableCircuitBreaker 开启断路器
 * @EnableHystrix 子类继承了EnableCircuitBreaker
*/

public class ClientApplication {
    public static void main(String[] args) {
        //服务消费者
        SpringApplication.run(ClientApplication.class,args);

    }
}
