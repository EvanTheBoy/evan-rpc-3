package com.evan.evanrpc.bootstrap;

import com.evan.evanrpc.RpcApplication;

/**
 * 服务消费者启动类
 */
public class ConsumerBootstrap {
    public static void init() {
        // RPC 框架初始化
        RpcApplication.init();
    }
}
