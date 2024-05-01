package com.evan.evanrpc.bootstrap;

import com.evan.evanrpc.RpcApplication;
import com.evan.evanrpc.config.RegistryConfig;
import com.evan.evanrpc.config.RpcConfig;
import com.evan.evanrpc.model.ServiceMetaInfo;
import com.evan.evanrpc.model.ServiceRegisterInfo;
import com.evan.evanrpc.registry.LocalRegistry;
import com.evan.evanrpc.registry.Registry;
import com.evan.evanrpc.registry.RegistryFactory;
import com.evan.evanrpc.server.VertxHttpServer;

import java.util.List;

public class ProviderBootstrap {
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // RPC 框架初始化
        RpcApplication.init();

        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务到服务中心
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());
            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + "服务注册失败", e);
            }
        }

        // 启动 web 服务
        VertxHttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(rpcConfig.getServerPort());
    }
}
