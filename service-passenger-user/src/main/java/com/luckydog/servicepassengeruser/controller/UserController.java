package com.luckydog.servicepassengeruser.controller;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.request.VerificationCodeDTO;
import com.luckydog.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 22:12
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号：" + passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }

}
