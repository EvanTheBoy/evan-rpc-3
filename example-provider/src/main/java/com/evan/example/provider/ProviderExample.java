package com.evan.example.provider;

import com.evan.evanrpc.bootstrap.ProviderBootstrap;
import com.evan.evanrpc.model.ServiceRegisterInfo;
import com.evan.example.common.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务提供者
 */
public class ProviderExample {
    public static void main(String[] args) {
        // 注册服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> service = new ServiceRegisterInfo<>(UserService.class.getName(),
                UserServiceImpl.class);
        serviceRegisterInfoList.add(service);

        // 初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
