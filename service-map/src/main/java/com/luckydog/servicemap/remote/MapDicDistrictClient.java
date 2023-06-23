package com.luckydog.servicemap.remote;

import com.luckydog.internalcommon.constant.AmapConfigConstants;
import com.luckydog.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/6/23 12:07
 */

@Service
public class MapDicDistrictClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    public String dicDistrict(String keywords) {

        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("keywords=" + keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key=" + amapKey);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        return forEntity.getBody();
    }
}
