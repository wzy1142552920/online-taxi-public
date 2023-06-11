package com.luckydong.apipassenger.service;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.request.ForecastPriceDTO;
import com.luckydog.internalcommon.response.ForecastPriceResponse;
import com.luckydong.apipassenger.remote.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/5/18 7:04
 */

@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度：" + destLatitude);

        log.info("调用计价服务，计算价格");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(destLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecastPriceDTO);
        double price = forecast.getData().getPrice();

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }
}