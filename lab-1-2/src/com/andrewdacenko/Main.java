package com.andrewdacenko;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String path = "./files";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                FileMatcher fm = new FileMatcher(file);
                System.out.println(fm.getMatches());
            }
        }
    }
}
