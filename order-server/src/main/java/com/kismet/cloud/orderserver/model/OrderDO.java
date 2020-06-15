/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.orderserver.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kismet
 * @version V1.0
 * @since 2020-01-11 16:58
 */
@Setter
@Getter
public class OrderDO implements Serializable {

    private String orderNo;

    private Date createTime;

    private String productName;

    private BigDecimal productPrice;

    private Long userId;

    private String port;

}