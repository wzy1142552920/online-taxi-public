package com.luckydog.apiDriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/24 9:14
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "api-driver";
    }
}
