package com.evan.evanrpc.server.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

import io.vertx.core.buffer.Buffer;

public class VertxTcpClient {
    public void start() {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        vertx.createNetClient().connect(8888, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Connected to TCP server");
                NetSocket socket = res.result();
                // 发送数据
                for (int i = 0; i < 1000; i++) {
                    Buffer buffer = Buffer.buffer();
                    String str = "Hello, server!Hello, server!Hello, server!";
                    buffer.appendInt(0);
                    buffer.appendInt(str.getBytes().length);
                    buffer.appendBytes(str.getBytes());
                    socket.write(buffer);
                }
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
