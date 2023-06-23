package com.luckydog.servicemap.controller;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/23 12:38
 */

@RestController
public class DicDistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDicDistrict(String keywords) {
        return dicDistrictService.initDicDistrict(keywords);
    }
}
