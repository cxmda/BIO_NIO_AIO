package com.chen.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author chen
 * @create 2020-05-18 18:55
 */
public class SockerServer {
    public static void main(String[] args) {
        final String QUIT = "quit";
        final int  ADDRESS_PORT = 8888;
        ServerSocket serverSocket = null;

        try {
            //创建ServerSocket，绑定监听端口
            serverSocket = new ServerSocket(ADDRESS_PORT);

            while(true){
            //使用accept()阻塞等待客户端的连接
            Socket socket = serverSocket.accept();
                System.out.println("客户端【" + socket.getPort() + "】已连接");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                );
                String msg = null;
                while ((msg = reader.readLine()) != null){
                    System.out.println("读取到客户端【"+ socket.getPort() +"】发送的消息：" + msg);
                    writer.write("服务端收到消息了，你发过来的消息是：" + msg + "\n");
                    writer.flush();
                    if(QUIT.equals(msg)){
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
