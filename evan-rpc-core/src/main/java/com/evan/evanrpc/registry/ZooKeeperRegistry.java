package com.evan.evanrpc.registry;

import com.evan.evanrpc.config.RegistryConfig;
import com.evan.evanrpc.model.ServiceMetaInfo;

import java.util.List;

public class ZooKeeperRegistry implements Registry {
    @Override
    public void init(RegistryConfig registryConfig) {

    }

    @Override
    public void register(ServiceMetaInfo serviceMetaInfo) throws Exception {

    }

    @Override
    public void deregister(ServiceMetaInfo serviceMetaInfo) {

    }

    @Override
    public List<ServiceMetaInfo> serviceDiscovery(String serviceKey) {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void heartBeat() {

    }

    @Override
    public void watch(String serviceNodeKey) {

    }
}
