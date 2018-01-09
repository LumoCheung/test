package com.zlc.rpc.client;

import java.lang.reflect.Proxy;

public class ClientStub {
    private static String host = "localhost";
    private static int port = 8888;

    public static <T> T build(Class<T> tClass, String host, int port) {
        ClientStub.host = host;
        ClientStub.port = port;

        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, new ClientProxy());
    }

    public static String getHost() {
        return host;
    }

    public static int getPort() {
        return port;
    }
}
