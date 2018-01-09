package com.zlc.rpc.server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServiceStub {
    private static Map<String, Object> registerImpl = new HashMap<>();
    private static int port = 8888;

    public static void regist(Class<?> faceClass, Object impl) {
        registerImpl.put(faceClass.getName(), impl);
        System.out.println("service init success");
    }

    private static Object findServiceImpl(String className) {
        return registerImpl.get(className);
    }

    public static Object invoke(String className, String methodName, Class<?>[] argTypeClass, Object[] arg) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //  5）server stub根据解码结果调用本地的服务；
        Object serviceImpl = findServiceImpl(className);
        Method method = serviceImpl.getClass().getMethod(methodName, argTypeClass);
        //  6）本地服务执行并将结果返回给server stub；
        return method.invoke(serviceImpl, arg);
    }

    public static void publish(int port) throws IOException {
        ServiceStub.port = port;
        ServerSocket serverSocket = new ServerSocket(ServiceStub.port);
        System.out.println("service publish success");

        while (true) {
            try (
                    Socket socket = serverSocket.accept();

                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                    OutputStream outputStream = socket.getOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ) {
                //  4）server stub收到消息后进行解码；
                String className = objectInputStream.readUTF();

                String methodName = objectInputStream.readUTF();
                Class<?>[] argTypeClass = (Class<?>[]) objectInputStream.readObject();
                Object[] arg = (Object[]) objectInputStream.readObject();


                Object result = ServiceStub.invoke(className, methodName, argTypeClass, arg);


                //  7）server stub将返回结果打包成消息并发送至消费方；
                objectOutputStream.writeObject(result);
                objectOutputStream.flush();
            } catch (Exception e) {
                System.err.println(e);
            } finally {

            }
        }
    }
}
