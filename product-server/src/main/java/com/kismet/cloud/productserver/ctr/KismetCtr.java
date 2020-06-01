/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.kismet.cloud.productserver.ctr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenbw1
 * @since 2020/4/4
 */
@RestController
public class KismetCtr {
    @Value("${env}")
    private String env;
    @RequestMapping("env")
    public String env(){
        return env;
    }
}
