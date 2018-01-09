package com.zlc.rpc.client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try (
                Socket socket = new Socket(ClientStub.getHost(), ClientStub.getPort());

                OutputStream outputStream = pwdsocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ) {
            // 2）client stub接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体；
            // 3）client stub找到服务地址，并将消息发送到服务端；
            // 类名
            objectOutputStream.writeUTF(method.getDeclaringClass().getName());
            // 方法名
            objectOutputStream.writeUTF(method.getName());
            // 参数类型
            objectOutputStream.writeObject(method.getParameterTypes());
            // 参数列表
            objectOutputStream.writeObject(args);

            objectOutputStream.flush();
            // 8）client stub接收到消息，并进行解码；
            // 9）服务消费方得到最终结果。
            return objectInputStream.readObject();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
    }
}
