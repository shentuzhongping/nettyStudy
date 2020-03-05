package com.shentu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8888));
            Socket socket = serverSocket.accept();
            System.out.println("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
