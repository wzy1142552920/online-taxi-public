package com.luckydog.internalcommon.response;

import lombok.Data;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 8:08
 */

@Data
public class TokenResponse {

    private String accessToken;

    private String refreshToken;

}
