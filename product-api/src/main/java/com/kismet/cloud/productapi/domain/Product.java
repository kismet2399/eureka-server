/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.productapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kismet
 * @version V1.0
 * @since 2020-01-11 16:15
 */
@Getter
@Setter
public class Product implements Serializable {

    private Long id;//商品id

    private String name;//商品名称

    private BigDecimal price;//商品价格

    private int stock;//商品库存

}
