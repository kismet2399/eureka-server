package com.kismet.cloud.productserver.hystrix;

import com.kismet.cloud.productapi.ProductFeignApi;
import com.kismet.cloud.productapi.domain.Product;
import com.kismet.cloud.productapi.hystrix.ProductHystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kismet
 * @since 2020/1/18
 */
@Component
@Slf4j
public  class ProductHystrixImpl extends ProductHystrix {
    @Override
    public Product find(Long id) {
        log.info("Product find 熔断降级");
        return new Product();
    }

    @Override
    public List<Product> list() {
        log.info("Product list 熔断降级");
        return new ArrayList<>();
    }
}
