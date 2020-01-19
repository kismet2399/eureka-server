package com.kismet.cloud.productapi.hystrix;

import com.kismet.cloud.productapi.ProductFeignApi;
import com.kismet.cloud.productapi.domain.Product;

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
public class ProductHystrix implements ProductFeignApi {
    @Override
    public Product find(Long id) {
        log.info("Api--->Product find 熔断降级");
        return new Product();
    }

    @Override
    public List<Product> list() {
        log.info("Api--->Product list 熔断降级");
        return new ArrayList<>();
    }
}
