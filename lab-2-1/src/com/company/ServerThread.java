package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

class ServerThread extends Thread {
    private static final String DATABASE_FILE = "students.db";

    private ObjectOutputStream out;

    private ObjectInputStream in;

    private InetAddress addr;

    public ServerThread(Socket s) throws IOException {

        out = new ObjectOutputStream(s.getOutputStream());

        in = new ObjectInputStream(s.getInputStream());

        addr = s.getInetAddress();

    }

    public void run() {
        Object input;

        try {
            input = in.readObject();

            if (input instanceof Actions) {
                if (input.equals(Actions.GET_ALL)) {
                    MyServerThread.students = (ArrayList<Student>)SerializationUtil.deserialize(DATABASE_FILE);
                    out.writeObject(MyServerThread.students);
                    System.out.println("Returned all students to " + addr.getHostName());
                } else if (input.equals(Actions.GET_SECOND_BEST)) {
                    ArrayList<Student> secondCourseBestStudents = new ArrayList<>();

                    MyServerThread.students.forEach(p -> {
                        if (p.getMark() > 4 && p.getCourse().equals("2")) {
                            secondCourseBestStudents.add(p);
                        }
                    });

                    out.writeObject(secondCourseBestStudents);
                    System.out.println("Returned best students of second course to " + addr.getHostName());
                } else if (input.equals(Actions.GET_GOOD_FOREIGN)) {
                    ArrayList<Student> goodForeignStudents = new ArrayList<>();

                    MyServerThread.students.forEach(p -> {
                        if (p.getMark() > 3 && !p.getCountry().equals("Ukraine")) {
                            goodForeignStudents.add(p);
                        }
                    });

                    out.writeObject(goodForeignStudents);
                    System.out.println("Returned good foreign students to " + addr.getHostName());
                }

            } else if (input instanceof Student) {
                MyServerThread.students.add((Student) input);
                SerializationUtil.serialize(MyServerThread.students, DATABASE_FILE);
                System.out.println("Added new student");
                out.writeObject("Ok");
            } else if(input instanceof ArrayList) {
                MyServerThread.students = (ArrayList<Student>) input;
                SerializationUtil.serialize(MyServerThread.students, DATABASE_FILE);
                System.out.println("Updated list of Students");
                out.writeObject("Ok");
            }
        } catch (IOException e) {
            System.out.println("Disconnect11");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void disconnect() {
        try {
            System.out.println(addr.getHostName() + " disconnected");
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }

    }

}
