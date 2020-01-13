/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.orderserver.service;

import com.kismet.cloud.orderserver.domain.OrderInfo;

/**
 *
 * @author kismet
 * @version V1.0
 * @since 2020-01-11 17:02
 */
public interface OrderInfoService {
    /**
     * 保存订单
     *
     * @param userId
     * @param productId
     * @return
     */
    OrderInfo save(Long userId, Long productId);
}
