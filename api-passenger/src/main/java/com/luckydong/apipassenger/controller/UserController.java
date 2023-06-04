package com.luckydong.apipassenger.controller;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydong.apipassenger.request.VerificationCodeDTO;
import com.luckydong.apipassenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/5/17 23:02
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request) {

        String accessToken = request.getHeader("Authorization");

        return userService.getUserByAccessToken(accessToken);
    }
}
