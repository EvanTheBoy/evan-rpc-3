package com.evan.evanrpc.server.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

public class VertxTcpClient {
    public void start() {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        vertx.createNetClient().connect(8888, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Connected to TCP server");
                NetSocket socket = res.result();
                // 发送数据
                socket.write("Hello, server!");
                // 接收响应
                socket.handler(buff -> System.out.println("Received response from server: " + buff.toString()));
            } else {
                System.out.println("Failed to connect to TCP server");
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpClient().start();
    }
}
