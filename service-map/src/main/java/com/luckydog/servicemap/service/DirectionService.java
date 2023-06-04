package com.luckydog.servicemap.service;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.response.DirectionResponse;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/4 23:28
 */

@Service
public class DirectionService {

    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(123);
        directionResponse.setDuration(11);
        return ResponseResult.success(directionResponse);
    }
}
