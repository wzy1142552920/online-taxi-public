package com.luckydog.serviceprice.service;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.request.ForecastPriceDTO;
import com.luckydog.internalcommon.response.DirectionResponse;
import com.luckydog.internalcommon.response.ForecastPriceResponse;
import com.luckydog.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/4 22:57
 */

@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度：" + destLatitude);

        log.info("调用地图服务，查询距离和时长");

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("读取计价规则");

        log.info("根据距离、时长和计价规则，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(24.4);
        return ResponseResult.success(forecastPriceResponse);
    }
}
