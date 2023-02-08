package com.luckydog.serviceverificationcode.controller;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/9 7:46
 */

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {
        double random = Math.random();
        double code = (random * 9 + 1) * Math.pow(10, size - 1);
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode((int) code);
        return ResponseResult.success(response);
    }
}
