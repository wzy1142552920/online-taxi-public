package com.luckydog.internalcommon.request;

import lombok.Data;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/5/18 7:06
 */

@Data
public class ForecastPriceDTO {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;

}
