package com.luckydong.apipassenger.request;

import lombok.Data;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 8:01
 */

@Data
public class VerificationCodeDTO {

    private String passengerPhone;

    private String verificationCode;
}
