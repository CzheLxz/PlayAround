package com.czhe.sysmanage.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/2 11:20
 * @description
 **/
public class RpcServer implements Server {

    static ConcurrentMap<String, Class> resMap;

    static {
        resMap = new ConcurrentHashMap();
        resMap.put(TestService.class.getName(), TestServiceImpl.class);

    }

    @Override
    public void handler() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    //等待客户端输入
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    //获取客户端传过来的参数
                    String className = (String) in.readObject();
                    String methodName = (String) in.readObject();
                    Object[] args = (Object[]) in.readObject();
                    Class[] argsType = (Class[]) in.readObject();
                    //从注册表中获取服务的字节码
                    Class t = resMap.get(className);
                    //通过字节码对象获取构造器
                    Constructor constructor = t.getConstructor();
                    constructor.setAccessible(true);
                    //通过反射的方式创建对象并且执行对象的方法
                    Object invoke = t.getMethod(methodName, argsType).invoke(constructor.newInstance(), args);
                    //返回结果写回给客户端
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(invoke);
                    in.close();
                    objectOutputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}