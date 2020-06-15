package com.kismet.cloud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableFeignClients
@EnableRetry
@EnableCircuitBreaker
@EnableHystrixDashboard
@MapperScan("com.kismet.cloud.orderserver.mapper")
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private static final String ZK_ADDR = "192.168.181.10:2181,192.168.181.11:2181,192.168.181.12:2181";
    private static final int SESSION_TIMEOUT = 30000;
    private static final String SERVER_PATH = "/servers";
    private static final String SUB_PATH = "/secServer";
    @Value("${server.host}")
    private String ip;
    @Value("${server.port}")
    private String port;

    private volatile ZooKeeper zooKeeper;

    public static List<String> addrList = new ArrayList<>();

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
                // 1,获取对应地址列表(即子节点列表)
                genData();
                // 2,获取对应地址列表(即子节点列表)
                try {
                    zooKeeper.addWatch(SERVER_PATH, event1 -> genData(), AddWatchMode.PERSISTENT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return zooKeeper;

    }

    private void genData() {
        try {
            List<String> children = zooKeeper.getChildren(SERVER_PATH, null);
            if (CollectionUtils.isEmpty(children)) {
                return;
            }
            addrList = children.stream().map(item -> {
                String data = null;
                try {
                    data = new String(zooKeeper.getData(SERVER_PATH + "/" + item, null, new Stat()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return data;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("获取到zookeeper注册服务最新地址"+addrList);
    }
}
