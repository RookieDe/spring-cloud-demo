package com.demo.springcloud.lb;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hongde
 * @version 1.0.0
 * @Description 自定义轮询算法
 * @ClassName customizeRule.java
 * @createTime 2021-11-22 15:23:00
 */
public class CustomizeRule extends AbstractLoadBalancerRule {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }


    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }
                //自定义轮询算法
                int total = this.chooseRandomInt();
                int index = total % serverCount;

                server = upList.get(index);
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        System.err.println("getHost:"+server.getHost());
                        System.err.println("getHostPort:"+server.getHostPort());
                        System.err.println("getId:"+server.getId());
                        System.err.println("getPort:"+server.getPort());
                        System.err.println("getMetaInfo:"+server.getMetaInfo());
                        System.err.println("getScheme:"+server.getScheme());
                        System.err.println("getZone:"+server.getZone());
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    /**
     * 利用原子类，获取轮询index数据
     * @return
     */
    protected int chooseRandomInt() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.err.println("*********next:"+next);
        return next;
    }
}
