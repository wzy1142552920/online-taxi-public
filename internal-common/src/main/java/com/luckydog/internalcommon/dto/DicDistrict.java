package com.luckydog.internalcommon.dto;

import lombok.Data;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/11 12:01
 */

@Data
public class DicDistrict {

    private String addressCode;

    private String addressName;

    private String parentAddressCode;

    private Integer level;
}
