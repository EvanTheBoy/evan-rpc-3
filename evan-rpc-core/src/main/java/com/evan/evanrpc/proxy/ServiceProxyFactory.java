package com.evan.evanrpc.proxy;

import com.evan.evanrpc.RpcApplication;
import com.evan.evanrpc.proxy.mock.MockServiceProxyFactory;
import com.evan.evanrpc.proxy.normal.NormalServiceProxyFactory;

public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> clazz) {
        if (RpcApplication.getRpcConfig().isMock()) {
            return MockServiceProxyFactory.getMockProxy(clazz);
        }
        return NormalServiceProxyFactory.getProxy(clazz);
    }
}
