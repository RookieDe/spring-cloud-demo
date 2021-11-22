package com.demo.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName LoadBalancer.java
 * @createTime 2021-11-22 10:16:00
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
