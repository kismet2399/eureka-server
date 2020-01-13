/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.orderserver.service.impl;

import com.kismet.cloud.orderserver.domain.OrderInfo;
import com.kismet.cloud.orderserver.service.OrderInfoService;
import com.kismet.cloud.productapi.domain.Product;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author kismet
 * @version V1.0
 * @since 2020-01-11 17:03
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public OrderInfo save(Long userId, Long productId) {
        // 根据productId获取product对象
        Product p = restTemplate.getForObject("http://localhost:8081/find/" + productId,Product.class);
        OrderInfo info = new OrderInfo();
        info.setUserId(userId).setOrderNo(UUID.randomUUID().toString()).setCreateTime(new Date())
            .setProductPrice(p.getPrice()).setProductName(p.getName());
        System.out.println(info);
        return info;
    }
}
