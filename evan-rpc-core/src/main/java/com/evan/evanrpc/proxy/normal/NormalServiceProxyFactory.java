package com.evan.evanrpc.proxy.normal;

import java.lang.reflect.Proxy;

public class NormalServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass) {
//        if (RpcApplication.getRpcConfig().isMock()) {
//            return (T) Proxy.newProxyInstance(
//                    serviceClass.getClassLoader(),
//                    new Class[]{serviceClass},
//                    new MockServiceProxy()
//            );
//        }

        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new NormalServiceProxy()
        );
    }
}
