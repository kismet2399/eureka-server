package com.kismet.cloud.configserver.ctr;

import java.util.Objects;

import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kismet
 * @since 2020/5/23
 */
@RestController
public class ZKCtr {
    @Autowired
    private ZooKeeper zooKeeper;

    @RequestMapping("createNode")
    public String createNote(String path, String data, String type) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.valueOf(type));
    }

    @RequestMapping("getData")
    public String getData(String path) throws KeeperException, InterruptedException {
        // stat 版本
        return new String(zooKeeper.getData(path, false, new Stat()));
    }

    @RequestMapping("delete")
    public String delete(String path) throws KeeperException, InterruptedException {
        // stat 版本
        Stat exists = zooKeeper.exists(path, false);
        if (exists != null) {
            zooKeeper.delete(path, exists.getVersion());
            return "success";
        }
        return "failure";
    }

    @RequestMapping("update")
    public String update(String path, String data) throws KeeperException, InterruptedException {
        // stat 版本
        Stat exists = zooKeeper.exists(path, false);
        if (exists != null) {
            zooKeeper.setData(path, data.getBytes(), exists.getVersion());
            return "success";
        }
        return "failure";
    }

    @RequestMapping("addWatch1")
    public String addWatch1(String path) throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists(path, false);
        if (exists != null) {
            Watcher watcher = new Watcher() {
                @Override
                public void process(WatchedEvent event) { // 数据改变事件,而且是一次性事件
                    byte[] data = new byte[0];
                    try {
                        data = zooKeeper.getData(path, this, new Stat());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("更新后的数据:" + new String(data));
                }
            };
            zooKeeper.getData(path, watcher, new Stat());
            return "success";
        }
        return "success";
    }

    @RequestMapping("addWatch2")
    public String addWatch2(String path) throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists(path, false);
        if (exists != null) {
            zooKeeper.addWatch(path, event -> {
                if (Objects.equals(Watcher.Event.EventType.NodeDataChanged, event.getType())) {
                    // 重新获取数据
                    System.out.println("数据改变");
                    try {
                        byte[] data = zooKeeper.getData(path, false, new Stat());
                        System.out.println("接受的数据:" + new String(data));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (Objects.equals(Watcher.Event.EventType.NodeChildrenChanged, event.getType())) {
                    // 重新获取数据
                    System.out.println("子节点数据改变");
                }
            }, AddWatchMode.PERSISTENT);
            return "success";
        }
        return "success";
    }
}
