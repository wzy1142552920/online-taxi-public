package com.luckydog.internalcommon.dto;

import lombok.Data;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/6 23:21
 */

@Data
public class PriceRule {

    private String cityCode;

    private String vehicleType;

    private Double startFare;

    private Integer startMile;

    private Double unitPricePerMile;

    private Double unitPricePerMinute;

}
