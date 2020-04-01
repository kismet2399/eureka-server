/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.productserver.fegin;

import com.google.common.collect.ImmutableList;
import com.kismet.cloud.productapi.ProductFeignApi;
import com.kismet.cloud.productapi.domain.Product;
import com.kismet.cloud.productserver.manager.model.primary.mapper.CyOffsetStoreMapper;
import com.kismet.cloud.productserver.manager.model.second.mapper.DoctorTeamMapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kismet
 * @version V1.0
 * @since 2020-01-11 16:44
 */
@RestController
@Slf4j
public class ProductFeignClient implements ProductFeignApi {

    @Value("${server.port}")
    private String port;
    @Autowired
    private DoctorTeamMapper doctorTeamMapper;
    @Autowired
    private CyOffsetStoreMapper cyOffsetStoreMapper;

    @Override
    public Product find(@PathVariable("id") Long id) {
        log.info("查询订单服务");
        log.info(id + "");
        return new Product().setId(1L).setName("苹果").setPrice(BigDecimal.TEN).setStock(3).setPort(port);
    }

    @Override
    public List<Product> list() {
        log.info(doctorTeamMapper.selectByPrimaryKey("111") + "");
        log.info(cyOffsetStoreMapper.selectByPrimaryKey(11L) + "");
        return ImmutableList
            .of(new Product().setId(1L).setName("苹果").setPrice(BigDecimal.TEN).setStock(3).setPort(port));
    }
}
