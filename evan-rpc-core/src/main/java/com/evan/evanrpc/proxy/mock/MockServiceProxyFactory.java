package com.evan.evanrpc.proxy.mock;

import java.lang.reflect.Proxy;

public class MockServiceProxyFactory {
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy()
        );
    }
}
