package com.evan.evanrpc.registry;

import com.evan.evanrpc.model.ServiceMetaInfo;

import java.util.List;

public class RegistryServiceCache {
    /**
     * 服务消费端缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     * @param newServiceCache
     */
    public void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     * @return
     */
    public List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        this.serviceCache = null;
    }
}
