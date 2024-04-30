package com.evan.evanrpc.proxy.general;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.evan.evanrpc.RpcApplication;
import com.evan.evanrpc.config.RpcConfig;
import com.evan.evanrpc.constant.RpcConstant;
import com.evan.evanrpc.fault.retry.RetryStrategy;
import com.evan.evanrpc.fault.retry.RetryStrategyFactory;
import com.evan.evanrpc.loadbalancer.LoadBalancer;
import com.evan.evanrpc.loadbalancer.LoadBalancerFactory;
import com.evan.evanrpc.model.RpcRequest;
import com.evan.evanrpc.model.RpcResponse;
import com.evan.evanrpc.model.ServiceMetaInfo;
import com.evan.evanrpc.protocol.*;
import com.evan.evanrpc.registry.Registry;
import com.evan.evanrpc.registry.RegistryFactory;
import com.evan.evanrpc.serializer.Serializer;
import com.evan.evanrpc.serializer.SerializerFactory;
import com.evan.evanrpc.server.tcp.VertxTcpClient;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class GeneralServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        String serviceName = method.getDeclaringClass().getName();
        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            // 调用负载均衡器选择服务
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名(请求路径)作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
            // 发送 TCP 请求
            // 使用重试机制
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> VertxTcpClient
                    .doRequest(rpcRequest, selectedServiceMetaInfo));
            return rpcResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException("调用失败:" + e);
        }
    }
}
