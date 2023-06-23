package com.luckydog.servicemap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/4 23:21
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.luckydog.servicemap.mapper")
public class ServiceMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
