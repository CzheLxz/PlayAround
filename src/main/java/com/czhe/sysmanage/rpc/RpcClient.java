package com.czhe.sysmanage.rpc;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.Properties;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/2 10:11
 * @description
 **/
public class RpcClient implements Client {
    final static String RPC_SERVER_HOST = "RPC_SERVER_HOST";
    final static String RPC_SERVER_PORT = "RPC_SERVER_PORT";

    @Override
    public Object getService(Class t) {
        return Proxy.newProxyInstance(t.getClassLoader(), new Class[]{t}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //读取配置文件
                Properties properties = new Properties();
                InputStream inputStream = t.getClassLoader().getResourceAsStream("rpc/RpcConfig.properties");
                properties.load(inputStream);
                String host = properties.getProperty(RPC_SERVER_HOST);
                Integer port = new Integer(properties.getProperty(RPC_SERVER_PORT));
                //rpc注册过程
                Socket socket = new Socket(host, port);
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                //告诉服务端调用的是哪个类
                objectOutputStream.writeObject(t.getName());
                //告诉服务端调用的是哪个方法
                objectOutputStream.writeObject(method.getName());
                //告诉服务端调用方法传入的参数
                objectOutputStream.writeObject(args);
                //告诉服务端调用方法的参数类型
                objectOutputStream.writeObject(method.getParameterTypes());
                //完成序列化刷新
                objectOutputStream.flush();
                //接受服务端响应结果
                ObjectInputStream responce = new ObjectInputStream(socket.getInputStream());
                Object o = responce.readObject();

                objectOutputStream.close();
                responce.close();
                socket.close();
                return  o;

            }
        });
    }
}