package com.evan.example.provider;

import com.evan.evanrpc.RpcApplication;
import com.evan.evanrpc.config.RegistryConfig;
import com.evan.evanrpc.config.RpcConfig;
import com.evan.evanrpc.model.ServiceMetaInfo;
import com.evan.evanrpc.registry.LocalRegistry;
import com.evan.evanrpc.registry.Registry;
import com.evan.evanrpc.registry.RegistryFactory;
import com.evan.evanrpc.server.HttpServer;
import com.evan.evanrpc.server.VertxHttpServer;
import com.evan.example.common.service.UserService;

public class ProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到服务中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        System.out.println("serviceMetaInfo 的 HTTP 请求地址是: " +serviceMetaInfo.getServiceAddress());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
