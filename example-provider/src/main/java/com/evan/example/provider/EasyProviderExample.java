package com.evan.example.provider;

import com.evan.evanrpc.registry.LocalRegistry;
import com.evan.evanrpc.server.HttpServer;
import com.evan.evanrpc.server.VertxHttpServer;
import com.evan.example.common.service.UserService;

public class EasyProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
