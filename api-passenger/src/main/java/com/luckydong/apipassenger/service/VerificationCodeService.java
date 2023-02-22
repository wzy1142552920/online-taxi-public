package com.luckydong.apipassenger.service;

import com.luckydog.internalcommon.constant.CommonStatusEnum;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.response.NumberCodeResponse;
import com.luckydog.internalcommon.response.TokenResponse;
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
    private StringRedisTemplate stringRedisTemplate;

    // 乘客验证码的前缀
    private String verificationCodePrefix = "passenger-verification-code-";

    public String generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code = "111111";

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationCodeClient.getNumberCode(5);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("remote number code:"+numberCode);

        // 存入redis
        System.out.println("存入redis");
        String key = generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode+"", 2, TimeUnit.MINUTES);

        // 返回值
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }

    private String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        System.out.println("进入 checkCode 方法");
        // 去redis 读取验证码
        System.out.println("根据手机号，去Redis读取验证码");
        //生成key
        String key = generatorKeyByPhone(passengerPhone);
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

        // 颁发令牌
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}
