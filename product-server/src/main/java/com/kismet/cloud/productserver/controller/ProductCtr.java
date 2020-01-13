/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.productserver.controller;

import com.google.common.collect.ImmutableList;
import com.kismet.cloud.productapi.domain.Product;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kismet
 * @version V1.0
 * @since 2020-01-11 16:44
 */
@RestController
public class ProductCtr {

    @RequestMapping("find/{id}")
    public Product find(@PathVariable("id") Long id) {
        System.out.println(id);
        return new Product().setId(1L).setName("苹果").setPrice(BigDecimal.TEN).setStock(3);
    }

    @RequestMapping("list")
    public List<Product> list() {
        System.exit(0);
        return ImmutableList.of(new Product().setId(1L).setName("苹果").setPrice(BigDecimal.TEN).setStock(3));
    }
}
