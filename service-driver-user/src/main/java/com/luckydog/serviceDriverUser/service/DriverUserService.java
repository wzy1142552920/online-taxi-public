package com.luckydog.serviceDriverUser.service;

import com.luckydog.internalcommon.dto.DriverUser;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.serviceDriverUser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/23 23:06
 */

@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult testGetDriverUser() {
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }


}
