package com.luckydong.apipassenger.intercepter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.luckydog.internalcommon.constant.TokenConstant;
import com.luckydog.internalcommon.dto.ResponseResult;
import com.luckydog.internalcommon.dto.TokenResult;
import com.luckydog.internalcommon.utils.JwtUtils;
import com.luckydog.internalcommon.utils.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/23 8:13
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate  stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";
        String token = request.getHeader("Authorization");
        TokenResult tokenResult = JwtUtils.checkToken(token);
        if (tokenResult == null) {
            resultString = "access token invalid";
            result = false;
        } else {
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstant.ACCESS_TOKEN_TYPE);
            String tokenRedis =  stringRedisTemplate.opsForValue().get(tokenKey);
            if (StringUtils.isBlank(tokenRedis) || !token.trim().equals(tokenRedis.trim())) {
                resultString = "access token invalid";
                result = false;
            }
        }

        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return result;
    }
}
