package com.evan.evanrpc.registry;

import com.evan.evanrpc.config.RegistryConfig;
import com.evan.evanrpc.model.ServiceMetaInfo;

import java.util.List;

public interface Registry {
    /**
     * 初始化
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务(服务端)
     * @param serviceMetaInfo
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务(服务端)
     * @param serviceMetaInfo
     */
    void deregister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现(获取某服务的所有节点, 消费端)
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 消费端
     */
    void destroy();

    /**
     * 心跳检测(服务端)
     */
    void heartBeat();
}
