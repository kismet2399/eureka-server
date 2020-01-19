/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.orderserver.controller;

import com.kismet.cloud.orderserver.domain.OrderInfo;
import com.kismet.cloud.orderserver.service.OrderInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kismet
 * @version V1.0
 * @since 2020-01-11 17:01
 */
@RestController
public class OrderCtr {
    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "save", produces = { "application/json;charset=UTF-8" })
    @HystrixCommand(fallbackMethod = "saveForBack")
    public OrderInfo save(Long userId, Long productId) {
        System.out.println("保存订单操作");
        int a = 1 / 0;
        return orderInfoService.save(userId, productId);
    }

    public OrderInfo saveForBack(Long userId, Long productId) {
        System.out.println("保存订单降级操作");
        return orderInfoService.save(userId, productId);
    }
}
