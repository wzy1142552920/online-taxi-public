package com.luckydog.servicepassengeruser.dao;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：LuckyDog
 * @description：TODO
 * @date ：2023/2/22 22:36
 */
@Data
public class PassengerUser {

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;
}
