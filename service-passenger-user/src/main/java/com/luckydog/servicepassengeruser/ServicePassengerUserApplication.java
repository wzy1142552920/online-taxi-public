package com.luckydog.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 21:53
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.luckydog.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}
