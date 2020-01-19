/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.productapi;

import com.kismet.cloud.productapi.domain.Product;
import com.kismet.cloud.productapi.hystrix.ProductHystrix;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kismet
 * @version V1.0
 * @since 2020-01-18 13:25
 */
@FeignClient(value = "PRODUCT-SERVER", fallback = ProductHystrix.class)
public interface ProductFeignApi {
    @RequestMapping("find/{id}")
    Product find(@PathVariable("id") Long id);

    @RequestMapping("list")
    List<Product> list();
}
