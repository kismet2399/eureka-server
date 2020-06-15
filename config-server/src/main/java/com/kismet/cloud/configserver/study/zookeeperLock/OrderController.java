package com.kismet.cloud.configserver.study.zookeeperLock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hesj
 * @Created by 叩丁狼教育 hesj
 */
@RestController
public class OrderController {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ZooKeeper zooKeeper;
    @Value("${server.port}")
    private String port;

    private String path = "/locks";
    private String node = "/orderIdLock";

    @RequestMapping("createOrder")
    public String createOrder() throws Exception {
        //获取id
        if (tryLock()) { //尝试获取锁
            //调用业务方法 生成订单编号
            String id = restTemplate.getForObject("http://localhost:" + port + "/getId", String.class);
            System.out.println("获取到的id:" + id);
            //释放锁
            unlock();
        } else {
            waitLock();
        }
        return "success";
    }

    //尝试获取id, 如果获取到了, 返回true, 否则返回false
    //竞争锁资源
    public boolean tryLock() {
        try {
            zooKeeper.create(path + node, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //释放锁资源
    public void unlock() {
        //删除指定节点
        try {
            Stat stat = zooKeeper.exists(path + node, false);
            if (stat != null) {
                zooKeeper.delete(path + node, stat.getVersion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //阻塞状态 --等待锁
    public void waitLock() {
        //绑定监听时间
        try {
            //绑定的是一次性事件
            zooKeeper.getChildren(path, event -> {
                if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                    try {
                        createOrder();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
