package com.kismet.cloud.configserver.study.zookeeperLock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hesj
 * @Created by 叩丁狼教育 hesj
 */
@RestController
public class OrderIDGeneratorController {

    private  int count=0;

    @RequestMapping("getId")
    public String getId(){
        String id = null;
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            count=count+1;//并发的原子性, 有序性, 可见性
            id = sdf.format(new Date()) + "-" + count;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }
}
