package com.kismet.cloud.orderserver;

import com.kismet.cloud.OrderServerApplication;
import com.kismet.cloud.orderserver.mapper.CyOffsetStoreMapper;
import com.kismet.cloud.orderserver.model.CyOffsetStoreDO;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServerApplicationTests {
    @Autowired
    private CyOffsetStoreMapper cyOffsetStoreMapper;

    @Test
    void contextLoads() {
        Condition condition = new Condition(CyOffsetStoreDO.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("isDeleted", 1);
        cyOffsetStoreMapper.selectByCondition(condition);
        cyOffsetStoreMapper.selectByPrimaryKey("2");
        cyOffsetStoreMapper.testSelect(2L);
        System.out.println(cyOffsetStoreMapper.selectAll());
    }

}
