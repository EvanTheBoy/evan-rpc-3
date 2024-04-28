package com.evan.evanrpc.proxy.general;

import java.lang.reflect.Proxy;

public class GeneralServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new GeneralServiceProxy()
        );
    }
}
