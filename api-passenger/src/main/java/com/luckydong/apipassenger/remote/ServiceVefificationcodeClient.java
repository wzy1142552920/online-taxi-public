package com.luckydong.apipassenger.remote;

import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/9 8:16
 */

@FeignClient("service-verificationcode")
public interface ServiceVefificationcodeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}