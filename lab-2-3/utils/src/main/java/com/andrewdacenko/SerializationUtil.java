package com.andrewdacenko;

import java.io.*;

public class SerializationUtil {

    public static Object deserialize(String fileName) {
        Object obj = null;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();

            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static void serialize(Object obj, String fileName){
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}