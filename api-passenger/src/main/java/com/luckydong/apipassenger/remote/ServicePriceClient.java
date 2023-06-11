package com.luckydong.apipassenger.remote;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.request.ForecastPriceDTO;
import com.luckydog.internalcommon.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/11 9:05
 */

@FeignClient("service-price")
public interface ServicePriceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecast(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
