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
    public OrderInfo save(Long userId, Long productId) {
        return orderInfoService.save(userId, productId);
    }
}
