package com.luckydog.serviceprice.service;

import com.luckydog.internalcommon.constant.CommonStatusEnum;
import com.luckydog.internalcommon.dto.PriceRule;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.request.ForecastPriceDTO;
import com.luckydog.internalcommon.response.DirectionResponse;
import com.luckydog.internalcommon.response.ForecastPriceResponse;
import com.luckydog.internalcommon.utils.BigDecimalUtils;
import com.luckydog.serviceprice.mapper.PriceRuleMapper;
import com.luckydog.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PriceRuleMapper priceRuleMapper;


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
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code", "110000");
        queryMap.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if (priceRules.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule priceRule = priceRules.get(0);
        log.info("根据距离、时长和计价规则，计算价格");

        double price = getPrice(distance, duration, priceRule);
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 根据距离、时长、和计价规则，计算最终价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        double price = 0;

        //起步价
        Double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price, startFare);

        //里程费
        //总里程 m
        double distanceMile = BigDecimalUtils.divide(distance, 1000);
        //起步里程
        double startMile = (double) priceRule.getStartMile();
        double distanceSubtract = BigDecimalUtils.subtract(distanceMile, startMile);

        //最终收费的里程数
        double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        //计程单元 元/km
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        //里程价格
        double mileFare = BigDecimalUtils.multiply(mile, unitPricePerMile);
        price = BigDecimalUtils.add(price, mileFare);

        //时长费
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        //时长的分钟数
        double timeMinute = BigDecimalUtils.divide(duration, 60);

        //计时单价
        double timeFare = BigDecimalUtils.multiply(timeMinute, unitPricePerMinute);
        //时长费用
        price = BigDecimalUtils.add(price, timeFare);

        BigDecimal priceBigDecimal = new BigDecimal(price);
        priceBigDecimal = priceBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return priceBigDecimal.doubleValue();
    }
}
