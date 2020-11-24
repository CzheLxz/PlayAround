package com.czhe.sysmanage.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/11/18 15:56
 * @description 基于TCP的socket实现
 **/
public class SocketClient {
    public static void main(String[] args) throws IOException {
        try {
            //和服务器创建连接
            Socket socket = new Socket("localhost", 8088);
            //发给服务器的信息
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("长江长江我是黄河");
            pw.flush();
            socket.shutdownOutput();

            //从服务器接收消息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println(String.format("服务器返回信息: %s", info));
            }
            br.close();
            is.close();
            os.close();
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}