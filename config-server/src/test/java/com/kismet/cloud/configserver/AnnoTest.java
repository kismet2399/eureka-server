package com.kismet.cloud.configserver;

import com.kismet.cloud.configserver.condition.GirlFriend;
import com.kismet.cloud.configserver.condition.Money;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ConfigServerApplication.class})
class AnnoTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        System.out.println(applicationContext.getBeansOfType(GirlFriend.class).size());
        System.out.println(applicationContext.getBeansOfType(Money.class).size());
    }

}
