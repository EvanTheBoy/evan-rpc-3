package com.evan.evanrpc.config;

import lombok.Data;

@Data
public class RegistryConfig {
    /**
     * 注册中心类别
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2380";

    /**
     * 注册用户名
     */
    private String username;

    /**
     * 注册密码
     */
    private String password;

    /**
     * 超时时间(单位毫秒)
     */
    private Long timeout = 10000L;
}
