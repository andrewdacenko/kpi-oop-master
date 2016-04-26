package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServerThread implements Runnable {

    public static ArrayList<Student> students = new ArrayList<>();

    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(8071);

            System.out.println("initialized");

            while (true) {
                Socket sock = serverSocket.accept();

                System.out.println(sock.getInetAddress().getHostName() + "connected");

                ServerThread server = new ServerThread(sock);

                server.start();
            }

        } catch (IOException e) {
            System.err.println(e);
        }

    }
}