package com.czhe.sysmanage.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/11/18 16:55
 * @description 基于UDP的socket实现
 **/
public class UDPClient {

    public static void main(String[] args) {
        try {
            String sendMsg = "客户端发送的信息画画的BABY奔驰的野马和带刺的玫瑰";
            //获取服务器的地址
            InetAddress address = InetAddress.getByName("localhost");
            //创建packet 封装要发送的数据和服务器地址
            DatagramPacket packet = new DatagramPacket(sendMsg.getBytes(), sendMsg.getBytes().length, address, 8088);

            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}