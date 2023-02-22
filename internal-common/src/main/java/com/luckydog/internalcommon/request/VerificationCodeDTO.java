package com.luckydog.internalcommon.request;

import lombok.Data;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 22:19
 */

@Data
public class VerificationCodeDTO {

    private String passengerPhone;

    private String verificationCode;
}
