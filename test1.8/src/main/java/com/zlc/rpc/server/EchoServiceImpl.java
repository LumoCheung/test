package com.zlc.rpc.server;

public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String s) {
        System.out.println("request param is :"+s);
        return "server echo: " + s;
    }
}
