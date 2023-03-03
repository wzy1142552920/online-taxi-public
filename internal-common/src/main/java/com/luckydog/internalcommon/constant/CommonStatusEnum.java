package com.luckydog.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {

    SUCCESS(1, "success"),
    FAIL(0, "fail"),

    /**
     * 验证码错误提示 1000 - 1099
     */
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),

    /**
     * Token类提示：1100 - 1199
     */
    TOKEN_ERROR(1199, "token 错误");

    @Getter
    private int code;

    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
