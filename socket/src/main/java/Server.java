import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/9/24 11:59
 * @description 服务器端
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2000);
        System.out.println("服务器准备就绪!");
        System.out.println("服务器信息:"+server.getInetAddress()+"p: "+server.getLocalPort());

        //等待客户端连接
        while(true){
            Socket client = server.accept();//监听连接到一个客户端
            //客户端构建异步线程
            ClientHandler clientHandler = new ClientHandler(client);
            //启动线程
            clientHandler.start();
        }
    }
    //客户端消息处理
    public static class ClientHandler extends Thread{
        private Socket socket;
        private boolean flag = true;
        ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run(){
            super.run();
            System.out.println("新客户端连接: "+socket.getInetAddress()+"p"+socket.getPort());//客户端的

            try {
                //得到打印流 用户数据输出 服务器回送数据使用
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                //得到输入流 用于接收数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                do{
                    //客户端拿出一条数据
                    String str = socketInput.readLine();
                    if("bye".equalsIgnoreCase(str)){
                        flag = false;
                        //回送
                        socketOutput.println("bye");
                    }else{
                        //打印到屏幕 再回送数据长度
                        System.out.println(str);
                        socketOutput.println("回送: "+str.length());
                    }
                }while (flag);
                socketInput.close();
                socketOutput.close();
            } catch (IOException e) {
                System.out.println("-----连接异常断开");
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已退出: "+ socket.getInetAddress() +"port: "+ socket.getPort());

        }
    }
}
