package com.kismet.cloud.configserver;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

/**
 * @author kismet
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
//    private static final String ZK_ADDR = "192.168.181.10:2181,192.168.181.11:2181,192.168.181.12:2181";
    private static final String ZK_ADDR = "47.104.249.62:2181";
    private static final int SESSION_TIMEOUT = 30000;
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    //创建一个zookeeper的连接
    @Bean
    public ZooKeeper zooKeeper() throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);//并发等待
        // 第一个参数: 连接地址和端口 第二个参数: 会话超时时间, 第三个参数: 事件监听程序
        ZooKeeper zooKeeper = new ZooKeeper(ZK_ADDR, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("event = " + event);
                if(event.getState()== Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();//等待连接成功在返回对象
                }
            }
        });
        //        zooKeeper.getState(); connecting --> connected

        countDownLatch.await();
        return zooKeeper;
    }

}
