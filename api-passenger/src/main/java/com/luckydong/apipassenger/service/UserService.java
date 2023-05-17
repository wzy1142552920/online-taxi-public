package com.luckydong.apipassenger.service;

import com.luckydog.internalcommon.constant.CommonStatusEnum;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.dto.TokenResult;
import com.luckydog.internalcommon.utils.JwtUtils;
import com.luckydog.servicepassengeruser.dao.PassengerUser;
import com.luckydog.servicepassengeruser.mapper.PassengerUserMapper;
import com.luckydong.apipassenger.remote.ServicePassengerUserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/5/17 23:03
 */

@Service
@Slf4j
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);

        //解析AccessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号：" + phone);

        //根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone.getData());
    }

    /**
     * 根据手机号查询用户信息
     * @param passengerPhone
     * @return
     */

    public ResponseResult getUserByPhone(String passengerPhone) {
        //根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if (passengerUsers.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(),  CommonStatusEnum.USER_NOT_EXISTS.getValue());
        } else {
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }
}
