package com.luckydong.apipassenger.service;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.dto.TokenResult;
import com.luckydog.internalcommon.utils.JwtUtils;
import com.luckydog.servicepassengeruser.dao.PassengerUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/5/17 23:03
 */

@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);

        //解析AccessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号：" + phone);

        //根据手机号查询用户信息

        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("zhangsan");
        passengerUser.setProfilePhoto("photo");
        return ResponseResult.success(passengerUser);
    }
}
