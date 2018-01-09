package com.zlc.rpc.server;

import java.io.IOException;

public class Service {
    public static void main(String[] args) throws IOException {
        ServiceStub.regist(EchoService.class, new EchoServiceImpl());
        ServiceStub.publish(8888);
    }
}
