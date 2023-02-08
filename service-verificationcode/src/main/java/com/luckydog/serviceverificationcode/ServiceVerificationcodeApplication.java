package com.luckydog.serviceverificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/9 7:18
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerificationcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationcodeApplication.class, args);
    }

}
