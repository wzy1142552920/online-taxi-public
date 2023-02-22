package com.luckydong.apipassenger.service;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.response.NumberCodeResponse;
import com.luckydog.internalcommon.response.TokenResponse;
import com.luckydong.apipassenger.remote.ServiceVerificationcodeClient;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/9 8:20
 */

@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationcodeClient.ServiceVefificationcodeClient serviceVefificationcodeClient;

    public String generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code = "111111";

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(5);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("remote number code:"+numberCode);

        // 存入redis
        System.out.println("存入redis");

        // 返回值
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }

    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        System.out.println("进入 checkCode 方法");
        // 去redis 读取验证码

        // 校验验证码

        // 判断原来是否有用户

        // 颁发令牌
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("");
        return ResponseResult.success(tokenResponse);
    }
}
