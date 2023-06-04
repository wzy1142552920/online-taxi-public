package com.luckydog.servicemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/4 23:21
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class);
    }
}
