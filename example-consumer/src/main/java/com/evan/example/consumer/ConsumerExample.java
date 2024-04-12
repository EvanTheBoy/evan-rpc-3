package com.evan.example.consumer;

import com.evan.evanrpc.config.RpcConfig;
import com.evan.evanrpc.utils.ConfigUtils;

public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
