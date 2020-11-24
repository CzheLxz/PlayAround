package com.czhe.sysmanage.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/11/18 16:11
 * @description 基于TCP的socket实现
 **/
public class SocketServer {
    public static void main(String[] args) throws IOException {
        try {
            //创建服务端socket
            ServerSocket serverSocket = new ServerSocket(8088);

            //创建客户端socket
            Socket socket;

            //监听客户端连接
            while (true) {
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP: " + address.getHostAddress());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}