package com.luckydog.apiBoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/24 8:07
 */

@SpringBootApplication
@EnableFeignClients
public class apiBossApplication {

    public static void main(String[] args) {
        SpringApplication.run(apiBossApplication.class);
    }
}
