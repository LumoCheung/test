package com.zlc.rpc.client;

import com.zlc.rpc.server.EchoService;

import java.io.IOException;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EchoService echoService = ClientStub.build(EchoService.class, "localhost", 8888);
        // 1）服务消费方（client）调用以本地调用方式调用服务；
        Stream.of("a", "b", "c").forEach(s -> System.out.println(echoService.echo(s)));
    }
}
