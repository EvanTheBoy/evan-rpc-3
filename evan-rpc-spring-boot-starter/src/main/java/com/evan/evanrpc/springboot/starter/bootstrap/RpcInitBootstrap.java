package com.evan.evanrpc.springboot.starter.bootstrap;

import com.evan.evanrpc.RpcApplication;
import com.evan.evanrpc.config.RpcConfig;
import com.evan.evanrpc.server.tcp.VertxTcpServer;
import com.evan.evanrpc.springboot.starter.annotation.EnableRpc;
import io.vertx.grpc.VertxServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * RPC 框架启动
 */
@Slf4j
public class RpcInitBootstrap implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取 EnableRpc 注解的属性值
        boolean needServer = (boolean) importingClassMetadata.getAnnotationAttributes(EnableRpc.class.getName())
                .get("needServer");
        // RPC 框架初始化 (配置和注册中心)
        RpcApplication.init();

        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 启动服务器
        if (needServer) {
            VertxTcpServer vertxTcpServer = new VertxTcpServer();
            vertxTcpServer.doStart(rpcConfig.getServerPort());
        } else {
            log.info("不启动 server");
        }
    }
}
