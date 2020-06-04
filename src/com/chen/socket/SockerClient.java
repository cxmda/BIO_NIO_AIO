package com.chen.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author chen
 * @create 2020-05-18 18:55
 */
public class SockerClient {
    public static void main(String[] args) {
        final String QUIT = "quit";
        final String SERVER_ADDRESS = "127.0.0.1";
        final int SERVER_PORT = 8888;

        Socket socket = null;
        BufferedWriter writer = null;
        try {
            //创建Socket
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            while (true) {
                String input = consoleReader.readLine();
                //发送信息给服务端
                writer.write(input + "\n");
                writer.flush();

                //读取服务端返回的消息
                String msg = reader.readLine();
                System.out.println(msg);

                if (QUIT.equals(input)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
