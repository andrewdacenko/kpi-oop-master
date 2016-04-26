package com.company;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class MyClientThread implements Runnable {

    public void run() {
        Frame frame = new Frame("DKR");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static Object communicate(Object obj) {
        Object response = null;

        try(Socket s = new Socket(InetAddress.getLocalHost(), 8071);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream())) {
            out.writeObject(obj);
            response = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            response = new ArrayList<>();
        }

        return response;

    }

}