package com.luckydog.internalcommon.utils;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/26 8:35
 */
public class RedisPrefixUtils {

    // 乘客验证码的前缀
    private static String verificationCodePrefix = "passenger-verification-code-";

    //token存储的前缀
    private static String tokenPrefix = "token-";

    public static String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    public static String generatorTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }
}
