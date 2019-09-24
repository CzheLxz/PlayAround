import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/9/24 16:51
 * @description 客户端
 *
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        //超时时间
        socket.setSoTimeout(3000);
        //连接本地 端口号2000 超时时间是3s
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(),2000),3000);
        // public void connect(SocketAddress endpoint, int timeout)
        // socket 地址 = ip + 端口号
        System.out.println("已发起服务器连接,并进入后续流程-------");
        System.out.println("客户端信息" + socket.getLocalAddress()+" port: "+socket.getLocalPort());
        System.out.println("服务器信息" + socket.getInetAddress());

        try {
            todo(socket);  //发送接收数据
        }catch (Exception e){
            System.out.println("异常退出");
        }
        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo(Socket client) throws IOException {

        InputStream in = System.in;  //键盘输入流
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        //得到Socket输出流 并转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);


        //得到Socket输入流 并转换为BufferedReader
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do{
            //读取一行
            String string = input.readLine();
            //发送到服务器
            socketPrintStream.println(string);

            //从服务器读取一行
            String echo = socketBufferedReader.readLine();
            if ("bye".equalsIgnoreCase(echo)){
                flag = false;
            }else {
                System.out.println(echo);
            }
        }while (flag);
        //释放资源
        socketBufferedReader.close();
        socketPrintStream.close();
    }

}
