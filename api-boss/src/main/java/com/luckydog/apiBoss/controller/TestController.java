package com.luckydog.apiBoss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/24 8:09
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "api-boss";
    }
}
