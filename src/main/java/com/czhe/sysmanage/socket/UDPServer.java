package com.czhe.sysmanage.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
 * @author czhe
 * @version 1.0
 * @create 2020/11/18 17:01
 * @description
 **/
public class UDPServer {

    public static void main(String[] args) {
        try {
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            DatagramSocket socket = new DatagramSocket(8088);
            // 接收socket客户端发送的数据。如果未收到会一致阻塞
            socket.receive(packet);
            String receiveMsg = new String(packet.getData(), 0, packet.getLength());
            System.out.println(packet.getLength());
            System.out.println(receiveMsg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}