package com.luckydong.apipassenger.service;

import com.luckydog.internalcommon.constant.CommonStatusEnum;
import com.luckydog.internalcommon.constant.IdentityConstant;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.request.VerificationCodeDTO;
import com.luckydog.internalcommon.response.NumberCodeResponse;
import com.luckydog.internalcommon.response.TokenResponse;
import com.luckydog.internalcommon.utils.JwtUtils;
import com.luckydog.internalcommon.utils.RedisPrefixUtils;
import com.luckydong.apipassenger.remote.ServicePassengerUserClient;
import com.luckydong.apipassenger.remote.ServiceVerificationcodeClient;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/9 8:20
 */

@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationcodeClient.ServiceVefificationcodeClient serviceVerificationCodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ResponseResult generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        // 存入redis
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);
        return ResponseResult.success("");
    }



    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        System.out.println("进入 checkCode 方法");
        // 去redis 读取验证码
        System.out.println("根据手机号，去Redis读取验证码");
        //生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value：" + codeRedis);
        // 校验验证码
        if(StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().endsWith(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 判断原来是否有用户
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);
        String token = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);
        //将token存到Redis中
        String tokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);
        stringRedisTemplate.opsForValue().set(tokenKey, token, 30, TimeUnit.DAYS);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }
}
