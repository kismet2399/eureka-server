package com.kismet.cloud.productserver;

import java.io.IOException;
import java.util.Objects;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProductServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServerApplication.class, args);
    }

    private static final String ZK_ADDR = "192.168.181.10:2181,192.168.181.11:2181,192.168.181.12:2181";
    private static final int SESSION_TIMEOUT = 30000;
    private static final String SERVER_PATH = "/servers";
    private static final String SUB_PATH = "/secServer";
    @Value("${server.host}")
    private String ip;
    @Value("${server.port}")
    private String port;

    private ZooKeeper zooKeeper;

    /**
     * 模拟服务的注册与发现
     * 1,服务启动后连接zookeeper,注册自身信息
     * 2,创建临时顺序目录节点,并将ip端口信息设置未节点数据(从而实现的服务添加删除自动感知)
     * 3,监控子节点变更信息,从而通知客户端
     *
     * @return
     * @throws IOException
     */
    @Bean
    public ZooKeeper zooKeeper() throws IOException {

        // 连接客户端
        zooKeeper = new ZooKeeper(ZK_ADDR, SESSION_TIMEOUT, event -> {
            if (Objects.equals(event.getState(), Watcher.Event.KeeperState.SyncConnected)) {
                System.out.println("zookeeper客户端连接成功");
                // 注册自身端口信息
                try {
                    zooKeeper
                        .create(SERVER_PATH + SUB_PATH, (ip + ":" + port).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            CreateMode.EPHEMERAL_SEQUENTIAL);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return zooKeeper;

    }
}
