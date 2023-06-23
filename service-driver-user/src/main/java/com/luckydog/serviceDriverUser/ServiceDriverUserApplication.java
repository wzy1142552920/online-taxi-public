package com.luckydog.serviceDriverUser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/23 20:05
 */

@SpringBootApplication
@MapperScan("com.luckydog.serviceDriverUser.mapper")
public class ServiceDriverUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class);
    }
}
