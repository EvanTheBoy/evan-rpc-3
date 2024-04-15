package com.evan.evanrpc.proxy.normal;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.evan.evanrpc.RpcApplication;
import com.evan.evanrpc.model.RpcRequest;
import com.evan.evanrpc.model.RpcResponse;
import com.evan.evanrpc.serializer.JdkSerializer;
import com.evan.evanrpc.serializer.Serializer;
import com.evan.evanrpc.serializer.SerializerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NormalServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求
            String host = RpcApplication.getRpcConfig().getServerHost();
            Integer serverPort = RpcApplication.getRpcConfig().getServerPort();
            try (HttpResponse httpResponse = HttpRequest.post("http://" + host + ":" + serverPort)
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
