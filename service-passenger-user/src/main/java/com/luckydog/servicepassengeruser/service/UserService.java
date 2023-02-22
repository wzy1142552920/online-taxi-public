package com.luckydog.servicepassengeruser.service;

import com.luckydog.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 22:13
 */

@Service
public class UserService {

    public ResponseResult loginOrRegister(String passengerPhone) {
        System.out.println("user service 被调用，手机号：" + passengerPhone);

        //根据手机号查询用户信息

        //判断用户信息是否存在

        // 如果不存在，插入用户信息
        return ResponseResult.success("");
    }
}
