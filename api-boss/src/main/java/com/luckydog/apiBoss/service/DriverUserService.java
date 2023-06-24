package com.luckydog.apiBoss.service;

import com.luckydog.apiBoss.remote.ServiceDriverUserClient;
import com.luckydog.internalcommon.dto.DriverUser;
import com.luckydog.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/24 8:15
 */

@Service
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addDriverUser(DriverUser driverUser) {
        return serviceDriverUserClient.addDriverUser(driverUser);
    }
}
