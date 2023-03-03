package com.luckydong.apipassenger.controller;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.response.TokenResponse;
import com.luckydong.apipassenger.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/3/3 7:36
 */

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {
        String refreshTokenStr = tokenResponse.getRefreshToken();
        System.out.println("previous token is " + refreshTokenStr);
        return tokenService.refreshToken(refreshTokenStr);
    }


}
