package com.kismet.cloud.configserver;

import java.io.IOException;
import java.util.Objects;

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
    private static final String ZK_ADDR = "192.168.181.10:2181,192.168.181.11:2181,192.168.181.12:2181";
    private static final int SESSION_TIMEOUT = 30000;
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @Bean
    public ZooKeeper zooKeeper() throws IOException {

        return new ZooKeeper(ZK_ADDR,SESSION_TIMEOUT,(event)->{
            if (Objects.equals(event.getState(), Watcher.Event.KeeperState.SyncConnected)) {
                System.out.println("zookeeper客户端连接成功");
            }
        });
    }

}
