package com.luckydog.servicemap.service;

import com.luckydog.internalcommon.constant.AmapConfigConstants;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.servicemap.remote.MapDicDistrictClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/23 12:00
 */

@Service
public class DicDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    public ResponseResult initDicDistrict(String keywords) {

        //请求地图
        String dicDistrict = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrict);
        return ResponseResult.success("ok");
    }
}
