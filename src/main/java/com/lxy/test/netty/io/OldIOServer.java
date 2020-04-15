package com.lxy.test.netty.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lxy
 * @date 2020-04-11
 */
public class OldIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = new ServerSocket(7001);

        try {
            while (true){
                Socket socket = socketServer.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                byte[] bytes = new byte[4096];
                while (true){
                    int readCount = dataInputStream.read(bytes, 0, bytes.length - 1);
                    if (-1 == readCount){
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
