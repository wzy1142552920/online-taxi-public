package com.luckydog.serviceDriverUser.controller;

import com.luckydog.internalcommon.dto.DriverUser;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.serviceDriverUser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/24 0:25
 */

@RestController
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {
        return driverUserService.addDriverUser(driverUser);
    }
}
