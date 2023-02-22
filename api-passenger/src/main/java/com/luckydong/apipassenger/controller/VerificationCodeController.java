package com.luckydong.apipassenger.controller;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydong.apipassenger.request.VerificationCodeDTO;
import com.luckydong.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 7:50
 */

@RestController
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        System.out.println("手机号：" + passengerPhone + "，验证码：" + verificationCode);
        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }


}
