package com.kismet.cloud.configserver.study.redis;

import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kismet
 * @since 2020/5/23
 */
@RestController
@RequestMapping("/redis")
public class RedisCtr {
    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/pipeline")
    public String pipeline(String path, String data, String type) throws KeeperException, InterruptedException {
        template.opsForValue().set("sss", "eee");
        long s1 = System.currentTimeMillis();
        template.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    template.opsForValue().set("sss" + i, "eee" + i);
                }
                return null;
            }
        });
        System.out.println(System.currentTimeMillis() - s1);
        long s2 = System.currentTimeMillis();
        for (int i = 10000; i < 15000; i++) {
            template.opsForValue().set("sss" + i, "eee" + i);
        }
        System.out.println(System.currentTimeMillis() - s2);
        return null;
    }
    @RequestMapping("produce")
    public String produce(String path, String data, String type) throws KeeperException, InterruptedException {
        for (int i = 0; i < 100000; i++) {
            template.convertAndSend("2399",i);
        }

        return null;
    }
}