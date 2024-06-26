package com.evan.example.consumer;

import com.evan.evanrpc.bootstrap.ConsumerBootstrap;
import com.evan.evanrpc.proxy.ServiceProxyFactory;
import com.evan.example.common.model.User;
import com.evan.example.common.service.UserService;

public class EasyConsumerExample {
    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("name: evan");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }

        long number = userService.getNumber();
        System.out.println(number);
    }
}
