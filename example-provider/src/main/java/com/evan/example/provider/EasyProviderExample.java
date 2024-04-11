package com.evan.example.provider;

import com.evan.evanrpc.RpcApplication;
import com.evan.evanrpc.registry.LocalRegistry;
import com.evan.evanrpc.server.HttpServer;
import com.evan.evanrpc.server.VertxHttpServer;
import com.evan.example.common.service.UserService;

public class EasyProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动服务器
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
