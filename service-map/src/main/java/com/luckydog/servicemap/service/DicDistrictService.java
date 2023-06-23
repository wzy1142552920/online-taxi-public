package com.luckydog.servicemap.service;

import com.luckydog.internalcommon.constant.AmapConfigConstants;
import com.luckydog.internalcommon.constant.CommonStatusEnum;
import com.luckydog.internalcommon.dto.DicDistrict;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.servicemap.mapper.DicDistrictMapper;
import com.luckydog.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords) {
        //请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrictResult);

        //解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status == -1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AmapConfigConstants.LEVEL);
            insertDicDistrict(countryAddressCode, countryAddressName, countryLevel, countryParentAddressCode);
            JSONArray provinceArray = countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int province = 0; province < provinceArray.size(); province++) {
                JSONObject provinceJsonObject = provinceArray.getJSONObject(province);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceAddressName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceParentAddressCode = countryAddressCode;
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);
                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceLevel, provinceParentAddressCode);
                JSONArray cityArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int city = 0; city < cityArray.size(); city++) {
                    JSONObject cityJsonObject = cityArray.getJSONObject(city);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);
                    insertDicDistrict(cityAddressCode, cityAddressName, cityLevel, cityParentAddressCode);
                    JSONArray districtArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int district = 0; district < districtArray.size(); district++) {
                        JSONObject districtJsonObject = districtArray.getJSONObject(district);
                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);
                        insertDicDistrict(districtAddressCode, districtAddressName, districtLevel, districtParentAddressCode);
                    }
                }
            }
        }
        return ResponseResult.success("OK");
    }

    private void insertDicDistrict(String addressCode, String addressName,
                                   String level, String parentAddressCode) {
        DicDistrict district = new DicDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        int levelInt = generateLevel(level);
        district.setLevel(levelInt);
        district.setParentAddressCode(parentAddressCode);
        dicDistrictMapper.insert(district);
    }

    private int generateLevel(String level) {
        int levelInt = 0;
        switch (level) {
            case "country":
                levelInt = 0;
                break;
            case "province":
                levelInt = 1;
                break;
            case "city":
                levelInt = 2;
                break;
            case "district":
                levelInt = 3;
                break;
        }
        return levelInt;
    }
}
