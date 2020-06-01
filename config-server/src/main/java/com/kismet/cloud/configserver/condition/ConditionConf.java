package com.kismet.cloud.configserver.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kismet
 * @since 2020/5/16
 */
@Configuration
public class ConditionConf {
    @Bean
    public Face face() {
        return new Face();
    }

//    @Bean
    public House house() {
        return new House();
    }

    @Bean
    public Money money() {
        return new Money();
    }

    @Bean
    @ConditionalBean(House.class)
    public GirlFriend girlFriend() {
        return new GirlFriend();
    }
}
