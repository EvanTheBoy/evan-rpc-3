package com.evan.evanrpc.springboot.starter.annotation;

import com.evan.evanrpc.constant.RpcConstant;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {
    /**
     * 服务接口类
     * @return
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 版本
     * @return
     */
    String serviceVersion() default RpcConstant.DEFAULT_SERVICE_VERSION;
}
